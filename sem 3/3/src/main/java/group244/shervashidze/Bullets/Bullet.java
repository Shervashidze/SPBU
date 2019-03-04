package group244.shervashidze.Bullets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/**
 * Class for working with bullet
 */
public abstract class Bullet {
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 700;
    private static final int G = 10;

    private int explodeRadius;
    private GraphicsContext graphicsContext;

    private boolean isDestroyed = false;

    private int x;
    private int x0;
    private int y;
    private int y0;

    private double timer = 0;
    private double angle;
    private double speed;

    private Paint paint;

    public void setExplodeRadius(int explodeRadius) {
        this.explodeRadius = explodeRadius;
    }

    public void setGraphicsContext(GraphicsContext gc) {
        graphicsContext = gc;
    }

    public void setSpeed(int sp) {
        speed = sp;
    }

    public void setX(int x) {
        this.x0 = x;
    }

    public void setY(int y) {
        this.y0 = y;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    /**
     * Drawing projectile
     */
    public void render() {
        graphicsContext.setFill(paint);
        graphicsContext.fillOval(x - bulletSize() / 2, y - bulletSize() / 2, bulletSize(), bulletSize());
        x = x0 + (int) (speed * cos(angle) * timer);
        y = (y0 + (int) (speed * sin(angle) * timer) + (int) (G * timer * timer / 2));
        timer += 0.1;

        if ((x < 0) || (x > MAX_WIDTH) || (y < 0) || (y > MAX_HEIGHT)) {
            destroy();
        }
    }

    protected abstract int bulletSize();

    /**
     * Destroyed the projectile
     */
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Checks whether the projectile is destroyed
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Returns explode radius
     */
    public int getRadius() {
        return explodeRadius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}