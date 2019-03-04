package group244.shervashidze.Bullets;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    @Override
    protected int bulletSize() {
        return 35;
    }
}
