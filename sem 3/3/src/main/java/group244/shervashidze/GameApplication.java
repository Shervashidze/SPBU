package group244.shervashidze;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * GameApplication play game as server or as client
 */
public class GameApplication extends Application {
    private static GridPane gridPane = new GridPane();
    private Scene scene = new Scene(gridPane, 500, 300);
    private static final int BASIC_WIDTH = 1000;
    private static final int BASIC_HEIGHT = 700;
    private static Button serverButton;
    private static TextField ipAddress;
    private static Button clientButton;
    private static Button connect;
    private Stage window;

    private volatile Game game;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final int START_X = 250;
    private static final int START_Y = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connecting");
        primaryStage.setResizable(false);

        initialize();

        serverButtonAction();
        connectButton();
        clientButton.setOnAction(event -> {
            serverButton.setDisable(true);
            clientButton.setDisable(true);
            connect.setDisable(false);
            ipAddress.setDisable(false);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        window = new Stage();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * creating grid pane with buttons to start game as client or server
     */
    private static void initialize() {
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setHgap(25);
        gridPane.setVgap(15);

        serverButton = new Button();
        serverButton.setText("Start Game as server");
        serverButton.setPrefSize(230, gridPane.getHeight() / 3);
        gridPane.add(serverButton, 0, 0);

        clientButton = new Button();
        clientButton.setText("Start Game as client");
        clientButton.setPrefSize(230, gridPane.getHeight() / 3);

        Label message = new Label("IP address: ");
        ipAddress = new TextField();
        ipAddress.setDisable(true);

        connect = new Button("connect");
        connect.setPrefSize(150, 40);
        connect.setDisable(true);

        gridPane.add(clientButton, 1, 0);
        gridPane.add(message, 1, 1);

        gridPane.add(connect, 1, 3);
        GridPane.setHalignment(connect, HPos.CENTER);
        GridPane.setValignment(connect, VPos.CENTER);

        gridPane.add(ipAddress, 1, 2);
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

            startClientGame();
        });
    }

    /**
     * actions on server button pressed
     */
    private void serverButtonAction() {
        serverButton.setOnAction(event -> {
            clientButton.setDisable(true);

            InetAddress thisIp = null;

            try {
                thisIp = InetAddress.getLocalHost();
            } catch (UnknownHostException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Can't get IP", ButtonType.CLOSE);
                alert.setHeaderText(null);
                alert.showAndWait();
                System.exit(1);
            }

            Label ip = new Label("Your IP address: " + thisIp.getHostAddress());
            gridPane.add(ip, 0, 4);
            serverButton.setDisable(true);

            game = new Server();

            startServerGame();
        });
    }

    /**
     * Realise game for client
     */
    private void startClientGame() {
        window.setTitle("Client Window");

        Group root = new Group();
        Scene scene = new Scene(root);
        window.setScene(scene);
        List<KeyCode> keys = keyboardSettings(scene);
        List<KeyCode> enemyKeys = enemyKeyboardSettings(game);

        Canvas canvas = new Canvas(BASIC_WIDTH, BASIC_HEIGHT + 40);
        root.getChildren().add(canvas);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Tank weapon = new Tank(graphicsContext, START_X, START_Y);
        Tank weapon2 = new Tank(graphicsContext, BASIC_WIDTH - START_X, START_Y);

        new GameMechanics(window, graphicsContext, keys, enemyKeys, weapon2, weapon, game);
        window.setResizable(false);
        window.show();
    }

    /**
     * Realise game for server
     */
    private void startServerGame() {
        window.setTitle("Server Window");

        Group root = new Group();
        Scene scene = new Scene(root);
        window.setScene(scene);
        List<KeyCode> keys = keyboardSettings(scene);
        List<KeyCode> enemyKeys = enemyKeyboardSettings(game);

        Canvas canvas = new Canvas(BASIC_WIDTH, BASIC_HEIGHT + 40);
        root.getChildren().add(canvas);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Tank weapon = new Tank(graphicsContext, START_X, START_Y);
        Tank weapon2 = new Tank(graphicsContext, BASIC_WIDTH - START_X, START_Y);

        new GameMechanics(window, graphicsContext, keys, enemyKeys, weapon, weapon2, game);
        window.setResizable(false);
        window.show();
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
