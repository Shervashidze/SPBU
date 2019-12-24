package group244.shervashidze.Bullets;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for working with medium bullets
 * compromise between speed and explosion radius.
 */
public class NormalBullet extends Bullet {

    public NormalBullet(GraphicsContext graphicsContext, Vec2d start, double angle) {
        setX((int) start.x);
        setY((int) start.y);
        setAngle(angle);
        setPaint(Color.BLUE);
        setGraphicsContext(graphicsContext);
        setExplodeRadius(45);
        setSpeed(80);
    }

    /**
     *
     * @return bullet size
     */
    @Override
    protected int bulletSize() {
        return 23;
    }
}
