package group244.shervashidze.Bullets;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for working with small bullets
 * fast but with small explosion radius
 */
public class SmallBullet extends Bullet {

    public SmallBullet(GraphicsContext graphicsContext, Vec2d start, double angle) {
        setX((int) start.x);
        setY((int) start.y);
        setAngle(angle);
        setPaint(Color.RED);
        setGraphicsContext(graphicsContext);
        setExplodeRadius(25);
        setSpeed(100);
    }

    /**
     *
     * @return bullet size
     */
    @Override
    protected int bulletSize() {
        return 15;
    }
}
