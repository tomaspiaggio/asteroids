package model.geommetrics;

import com.sun.istack.internal.NotNull;

/**
 * Created by Tomas on 10/11/17.
 */
public class Point2D {

    public double x;
    public double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void sum(@NotNull Point2D point) {
        x += point.x;
        y += point.y;
    }

    @Override
    public String toString() {
        return "(X: " + x + ", Y: " + y + ")";
    }
}
