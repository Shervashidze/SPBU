package group244.shervashidze;

import group244.shervashidze.Bullets.*;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for working with weapons
 */
public class Tank {
    private static final int GUN_SIZE = 20;
    private static final int VERTICAL_SHIFT = 2;

    private final GraphicsContext graphicsContext;

    private int x;
    private int y;
    private double angle;
    private static final int HORIZONTAL_SHIFT = 2;
    private int bulletsType = 1;

    /**
     * Constructor
     */
    Tank (GraphicsContext gc, int x, int y) {
        this.graphicsContext = gc;
        this.x = x;
        this.y = y;
        this.angle = -90;
    }

    public int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private void setX(int x) {
        int maxWidth = 1000;
        if ((x < 0) || (x > maxWidth)) {
            return;
        }
        this.x = x;
    }

    public void setY(int y) {
        int maxHeight = 700;
        if ((y < 0) || (y > maxHeight)) {
            return;
        }

        this.y = y;
    }

    /** Method for raising weapon */
    public void gunUp() {
        if (angle < -225) {
            return;
        }
        angle -= VERTICAL_SHIFT;
    }

    /** Method for lowering weapon */
    public void gunDown() {
        if (angle > 45) {
            return;
        }
        angle += VERTICAL_SHIFT;
    }

    /** Method for moving weapons to the right */
    public void moveRight() {
        this.setX(this.getX() + HORIZONTAL_SHIFT);
    }

    /** Method for moving weapons to the left */
    public void moveLeft() {
        this.setX(this.getX() - HORIZONTAL_SHIFT);
    }

    /**
     * Method for drawing weapon
     */
    public void draw() {
        graphicsContext.setLineWidth(8);
        graphicsContext.strokeLine(x, y, x + Math.cos(Math.PI * angle / 180) * GUN_SIZE,
                y + Math.sin(Math.PI * angle / 180) * GUN_SIZE);
        int weaponSize = 20;
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillOval(x - weaponSize / 2, y - weaponSize / 2, weaponSize, weaponSize);
    }

    /**
     * Method, which realise shot
     */
    public Bullet shot() {
        double startFi = (Math.PI * angle / 180);
        Vec2d start = new Vec2d((x + Math.cos(startFi) * GUN_SIZE) + 3, (y + Math.sin(startFi) * GUN_SIZE));
        if (bulletsType == 1) {
            return new SmallBullet(graphicsContext, start, Math.PI * angle / 180);
        } else if (bulletsType == 2) {
            return new BigBullet(graphicsContext, start, Math.PI * angle / 180);
        } else {
            return new NormalBullet(graphicsContext, start, Math.PI * angle / 180);
        }
    }

    public void switchBullets(int type) {
        bulletsType = type;
    }

    /**
     * Checks whether the weapon is destroyed
     */
    public boolean weaponDestroy(Bullet projectile) {
        return Math.pow(this.getX() - projectile.getX(), 2) + Math.pow(this.getY() - projectile.getY(), 2)
                < Math.pow(projectile.getRadius(), 2);
    }
}