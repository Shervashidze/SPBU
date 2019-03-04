package group244.shervashidze;

import group244.shervashidze.Bullets.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

/** Class for using Map */
public class Map {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 1000;

    private static final int BLOCK_SIZE = 100;

    private static final int[] MAP_Y = {0, 0, 0, 1, 2, 1, 0, 0, 1, 2, 1, 0, 0};
    private static final int[] MAP_X = {0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10};

    private final GraphicsContext graphicsContext;

    /**
     * Constructor
     */
    Map(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    /**
     * Method for drawing the map
     */
    public void draw() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
        double[] DMAP_X = Arrays.stream(MAP_X).mapToDouble(x -> (double) x)
                .map(x -> x * BLOCK_SIZE)
                .toArray();
        double[] DMAP_Y = Arrays.stream(MAP_Y).mapToDouble(x -> (double) x)
                .map(x -> 7 -x)
                .map(x -> x * BLOCK_SIZE)
                .toArray();
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillPolygon(DMAP_X, DMAP_Y, MAP_X.length);
    }

    /** Method that putting object on the ground */
    public void putOnTheGround(Tank obj) {
        obj.setY(getGroundY(obj.getX()));
    }

    /** Method that checks whether an object is on the ground */
    public boolean isOnTheGround(Bullet obj) {
        return obj.getY() > getGroundY(obj.getX());
    }

    /**
     * Returns the y coordinate on earth at the x coordinate
     */
    private int getGroundY(int x) {
        int blockNumber = x / BLOCK_SIZE;
        return HEIGHT - MAP_Y[blockNumber + 1] * BLOCK_SIZE -
                (MAP_Y[blockNumber + 2] - MAP_Y[blockNumber +1]) * (x - blockNumber * BLOCK_SIZE);
    }
}
