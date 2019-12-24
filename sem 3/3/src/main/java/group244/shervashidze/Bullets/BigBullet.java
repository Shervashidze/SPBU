package group244.shervashidze.Bullets;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for working with big bullets
 * slow and big projectile
 */
public class BigBullet extends Bullet {

    public BigBullet(GraphicsContext graphicsContext, Vec2d start, double angle) {
        setX((int) start.x);
        setY((int) start.y);
        setAngle(angle);
        setPaint(Color.BLACK);
        setGraphicsContext(graphicsContext);
        setExplodeRadius(60);
        setSpeed(60);
    }

    /**
     *
     * @return bullet size
     */
    @Override
    protected int bulletSize() {
        return 30;
    }
}
