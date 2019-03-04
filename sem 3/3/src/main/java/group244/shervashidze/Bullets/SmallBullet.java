package group244.shervashidze.Bullets;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    @Override
    protected int bulletSize() {
        return 15;
    }
}
