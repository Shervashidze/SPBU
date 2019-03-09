package group244.shervashidze;

import group244.shervashidze.Bullets.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static group244.shervashidze.ExitWindows.escapeeWindow;
import static group244.shervashidze.ExitWindows.exitWindow;
import static java.util.stream.Collectors.toList;

public class ClientApl extends Application {
    private static GridPane gridPane = new GridPane();
    private Scene scene = new Scene(gridPane, 300, 300);
    private static final int BASIC_WIDTH = 1000;
    private static final int BASIC_HEIGHT = 700;
    private static Button clientButton;
    private static Button connect;

    private volatile Game game;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final int START_X = 250;
    private static final int START_Y = 0;
    private static TextField ipAddress;
    private boolean isEnded = false;
    private Stage window;
    private static final Set<KeyCode> SUPPORTED_KEYS = EnumSet.of(
            KeyCode.LEFT,
            KeyCode.RIGHT,
            KeyCode.ENTER,
            KeyCode.UP,
            KeyCode.DOWN,
            KeyCode.ESCAPE,
            KeyCode.DIGIT1,
            KeyCode.DIGIT2,
            KeyCode.DIGIT3
    );


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connecting");
        primaryStage.setResizable(false);

        initialize();

        clientButton.setOnAction(event -> {
            clientButton.setDisable(true);
            connect.setDisable(false);
            ipAddress.setDisable(false);
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        connectButton();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Create start dialog window
     */
    private static void initialize() {
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setHgap(25);
        gridPane.setVgap(15);

        clientButton = new Button();
        clientButton.setText("Start Game");
        clientButton.setPrefSize(230, gridPane.getHeight() / 5);

        Label message = new Label("IP address: ");

        ipAddress = new TextField();
        ipAddress.setDisable(true);

        connect = new Button("connect");
        connect.setPrefSize(150, 40);
        connect.setDisable(true);

        gridPane.add(clientButton, 0, 0);
        gridPane.add(message, 0, 1);

        gridPane.add(connect, 0, 3);
        GridPane.setHalignment(connect, HPos.CENTER);
        GridPane.setValignment(connect, VPos.CENTER);

        gridPane.add(ipAddress, 0, 2);
    }

    /**
     * Actions on the connect button
     */
    private void connectButton() {
        connect.setOnAction(event -> {
            String ipAddressText = ipAddress.getText();
            try {
                if (!InetAddress.getLocalHost().getHostAddress().equals(ipAddressText)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Wrong IP", ButtonType.CLOSE);
                    alert.setHeaderText(null);
                    alert.setTitle("error");
                    alert.showAndWait();
                    System.exit(1);
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Couldn't get I/O for the connection to: " + ipAddressText, ButtonType.CLOSE);
                alert.setHeaderText(null);
                alert.setTitle("error");
                alert.showAndWait();
                System.exit(1);
            }
            connect.setDisable(true);
            ipAddress.setDisable(true);

            game = new Client(ipAddressText);

            gameForClient();
        });
    }

    /** Creates list of KeyCode */
    private LinkedList<KeyCode> keyboardSettings(Scene scene) {
        LinkedList<KeyCode> input = new LinkedList<>();
        scene.setOnKeyPressed(
                keyEvent -> {
                    KeyCode code = keyEvent.getCode();

                    if (!input.contains(code)) {
                        input.add(code);
                    }
                });

        scene.setOnKeyReleased(
                keyEvent -> {
                    KeyCode code = keyEvent.getCode();
                    input.remove(code);
                });
        return input;
    }

    /**
     * Returns a list of received values
     */
    private List<KeyCode> enemyKeyboardSettings(Game game) {
        List<KeyCode> input = new CopyOnWriteArrayList<>();

        executor.submit(() -> {
            while (true) {
                KeyCode key = game.receive();
                if (key != null) {
                    input.add(key);
                }

            }
        });

        return input;
    }

    /**
     * Realise game for client
     */
    private void gameForClient() {
        window = new Stage();
        window.setFullScreen(true);
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.setTitle("Client Window");

        Group root = new Group();
        Scene scene = new Scene(root);
        window.setScene(scene);
        List<KeyCode> keys = keyboardSettings(scene);
        List<KeyCode> counterpartyKeys = enemyKeyboardSettings(game);

        int screenWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        int screenHeight = (int) Screen.getPrimary().getVisualBounds().getHeight() + 40;
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add(canvas);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.scale((double) screenWidth / BASIC_WIDTH, (double) screenHeight / BASIC_HEIGHT);

        Tank weapon = new Tank(graphicsContext, START_X, START_Y);
        Tank weapon2 = new Tank(graphicsContext, BASIC_WIDTH - START_X, START_Y);

        gameMechanics(window, graphicsContext, keys, counterpartyKeys, weapon2, weapon);
        window.show();
    }

    /**
     * Method for realise game mechanics
     * @param primaryStage yor stage
     * @param graphicsContext your graphics context
     * @param keys your code keys
     * @param enemy code keys your enemy
     * @param weapon1 your weapon
     * @param weapon2 weapon your enemy
     */
    private void gameMechanics(Stage primaryStage, GraphicsContext graphicsContext,
                               List<KeyCode> keys, List<KeyCode> enemy, Tank weapon1, Tank weapon2) {
        Map map = new Map(graphicsContext);
        map.putOnTheGround(weapon1);
        map.putOnTheGround(weapon2);
        List<Bullet> projectiles = new LinkedList<>();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                map.draw();
                List<KeyCode> appliedKeys = updateWeapon(map, weapon1, keys, primaryStage, projectiles);
                updateWeapon(map, weapon2, enemy, primaryStage, projectiles);
                for (Bullet projectile : projectiles) {
                    if (map.isOnTheGround(projectile)) {

                        if (weapon1.weaponDestroy(projectile)) {
                            if (!isEnded) {
                                isEnded = true;
                                primaryStage.close();
                                exitWindow("lost", primaryStage.getTitle());
                            }
                        }

                        if (weapon2.weaponDestroy(projectile)) {
                            if (!isEnded) {
                                isEnded = true;
                                primaryStage.close();
                                exitWindow("won", primaryStage.getTitle());
                            }
                        }
                    }
                }

                cleanBullets(map, projectiles);
                projectiles.forEach(Bullet::render);

                interactWithEnemy(appliedKeys);
            }
        }.start();
    }

    /**
     * Apply processCommand to keys
     */
    private void interactWithEnemy(List<KeyCode> keys) {
        List<KeyCode> codes = keys.stream().filter(SUPPORTED_KEYS::contains).collect(toList());
        processCommand(codes);
        codes.clear();
    }

    /**
     * Sends commands
     */
    private void processCommand(List<KeyCode> commands) {
        game.send(commands);
    }

    /**
     * Method for update weapon
     * @return list from KeyCode commands
     */
    private List<KeyCode> updateWeapon(Map map, Tank weapon, List<KeyCode> keys, Stage primaryStage, List<Bullet> projectiles) {
        List<KeyCode> appliedKeys = new ArrayList<>();

        if (keys.contains(KeyCode.LEFT)) {
            weapon.moveLeft();
            map.putOnTheGround(weapon);
            appliedKeys.add(KeyCode.LEFT);
            keys.remove(KeyCode.LEFT);
        }

        if (keys.contains(KeyCode.RIGHT)) {
            weapon.moveRight();
            map.putOnTheGround(weapon);
            appliedKeys.add(KeyCode.RIGHT);
            keys.remove(KeyCode.RIGHT);
        }

        if (keys.contains(KeyCode.UP)) {
            weapon.gunUp();
            appliedKeys.add(KeyCode.UP);
            keys.remove(KeyCode.UP);
        }

        if (keys.contains(KeyCode.DOWN)) {
            weapon.gunDown();
            appliedKeys.add(KeyCode.DOWN);
            keys.remove(KeyCode.DOWN);
        }

        weapon.draw();

        if (keys.contains(KeyCode.ENTER)) {
            projectiles.add(weapon.shot());
            appliedKeys.add(KeyCode.ENTER);
            keys.remove(KeyCode.ENTER);
        }

        if (keys.contains(KeyCode.DIGIT1)) {
            weapon.switchBullets(1);
            appliedKeys.add(KeyCode.DIGIT1);
            keys.remove(KeyCode.DIGIT1);
        }

        if (keys.contains(KeyCode.DIGIT2)) {
            weapon.switchBullets(2);
            appliedKeys.add(KeyCode.DIGIT2);
            keys.remove(KeyCode.DIGIT2);
        }

        if (keys.contains(KeyCode.DIGIT3)) {
            weapon.switchBullets(3);
            appliedKeys.add(KeyCode.DIGIT3);
            keys.remove(KeyCode.DIGIT3);
        }

        if (keys.contains(KeyCode.ESCAPE)) {
            appliedKeys.add(KeyCode.ESCAPE);
            keys.remove(KeyCode.ESCAPE);
            primaryStage.close();
            escapeeWindow();
        }

        return appliedKeys;
    }

    /**
     * Cleans Bullets, which is on the ground
     */
    private void cleanBullets(Map map, List<Bullet> projectiles) {
        LinkedList<Bullet> toRemove = new LinkedList<>();

        for (Bullet projectile : projectiles) {
            if (map.isOnTheGround(projectile)) {
                projectile.destroy();
            }

            if (projectile.isDestroyed()) {
                toRemove.add(projectile);
            }
        }

        for (Bullet projectile : toRemove) {
            projectiles.remove(projectile);
        }
    }

    @Override
    public void stop() {
        window.close();
        game.close();
        executor.shutdownNow();
        try {
            super.stop();
        } catch (Exception e) {
            //yep
        }
    }
}
