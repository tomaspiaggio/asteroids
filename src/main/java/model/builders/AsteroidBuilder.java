package model.builders;

import model.asteroids.Asteroid;
import model.geommetrics.Point2D;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tomas on 10/11/17.
 */
public class AsteroidBuilder implements Builder<Asteroid> {

    private Point2D randomPointWithinCircle(double radius) {
        final double rSquared = radius*radius;
        final double x = (Math.random() * (radius*2)) - radius;
        final double y = (((Math.random() * 2) - 1) * Math.sqrt(rSquared - x*x));
        return new Point2D(x, y);
    }

    private Collection<Point2D> circleCenters(int vertices, int radius) {
        final double length = 0;
        final double angle = 360/vertices;
        final Collection<Point2D> points = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            final int x = 0;
        }
    }

    private Collection<Point2D> normalizePoints(Collection<Point2D> points) {
        Point2D min = new Point2D(0, 0);
        for (Point2D p: points) {
            if(p.x < min.x) min.x = p.x;
            if(p.y < min.y) min.y = p.y;
        }
        for(Point2D p: points){
            p.x += min.x;
            p.y += min.y;
        }
        return points;
    }

    @Override
    public Asteroid build() {
        return null;
    }
}
