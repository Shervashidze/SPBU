package group244.shervashidze;

import group244.shervashidze.Bullets.Bullet;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.*;

import static group244.shervashidze.ExitWindows.escapeeWindow;
import static group244.shervashidze.ExitWindows.exitWindow;
import static java.util.stream.Collectors.toList;

public class GameMechanics {

    private boolean isEnded;
    private volatile Game game;

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

    /**
     * Method for realise game mechanics
     * @param primaryStage yor stage
     * @param graphicsContext your graphics context
     * @param keys your code keys
     * @param enemy code keys your enemy
     * @param weapon1 your weapon
     * @param weapon2 weapon your enemy
     */
    public GameMechanics(Stage primaryStage, GraphicsContext graphicsContext,
                               List<KeyCode> keys, List<KeyCode> enemy, Tank weapon1, Tank weapon2, Game game) {
        this.game = game;
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
}
