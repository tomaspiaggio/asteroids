package model.builders;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;
import model.asteroids.Asteroid;
import model.geommetrics.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/11/17.
 */
public class AsteroidBuilder implements Builder<Asteroid> {

    private int vertices;
    private int radius;
    private int damage;
    private int speed;
    private final int width;
    private final int height;
    private Shape shape;
    private List<Vector2> positions;

    public AsteroidBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        positions.add(new Vector2(0, height / 3));
        positions.add(new Vector2(0, height / 3 * 2));
        positions.add(new Vector2(width, height / 3 * 2));
        positions.add(new Vector2(width, height / 3 * 2));
        positions.add(new Vector2(width / 3, 0));
        positions.add(new Vector2(width / 3 * 2, 0));
        positions.add(new Vector2(width / 3, height));
        positions.add(new Vector2(width / 3 * 2, height));
    }

    private Point2D randomPointWithinCircle(double radius) {
        final double rSquared = radius*radius;
        final double x = (Math.random() * (radius*2)) - radius;
        final double y = (((Math.random() * 2) - 1) * Math.sqrt(rSquared - x*x));
        return new Point2D(x, y);
    }

    private List<Point2D> polygonVertices(int vertices, double radius) {
        final double polygonRadius = (2 * radius) / (2 * Math.sin(180 / vertices));
        final List<Point2D> points = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            final double x = polygonRadius * Math.cos(2 * Math.PI * i / vertices);
            final double y = polygonRadius * Math.sin(2 * Math.PI * i / vertices);
            points.add(new Point2D(x, y));
        }
        return points;
    }

    private List<Point2D> normalizePoints(List<Point2D> points) {
        Point2D min = new Point2D(0, 0);
        for (Point2D p: points) {
            if(p.x < min.x) min.x = p.x;
            if(p.y < min.y) min.y = p.y;
        }
        for(Point2D p: points){
            p.x -= min.x;
            p.y -= min.y;
        }
        return points;
    }

    private void makeShape() {
        final List<Point2D> pointsWithinCircle = new ArrayList<>();
        final List<Point2D> points = polygonVertices(vertices, 2 * radius);
        final List<Point2D> normalizedPoints = normalizePoints(points);
        for (int i = 0; i < 5; i++) {
            Point2D point = normalizedPoints.get(i);
            point.sum(randomPointWithinCircle(radius));
            pointsWithinCircle.add(point);
        }
//        TODO: ME FALTA HACER EL PATH Y GUARDARLO EN EL SHAPE
//        shape = new Shape
    }

    public AsteroidBuilder setVertices(int vertices) {
        this.vertices = Math.min(10, Math.max(3, vertices));
        this.radius = vertices * 10;
        this.damage = vertices * 100;
        this.speed = 100/vertices;
        makeShape();
        return this;
    }

    @Override
    public Asteroid build() {
        final Vector2 position = positions.get((int)((positions.size() - 1) * Math.random()));
        Vector2 direction = new Vector2(0, speed);
        if(position.x() == width) direction.rotateDeg((float) Math.random() * 180);
        else if (position.x() == 0) direction.rotateDeg(-(float)(Math.random() * 180));
        else if(position.y() == height) direction.rotateDeg(90 + (float)(Math.random() * 180));
        else direction.rotateDeg(-90 + (float)(Math.random() * 180));
        return new Asteroid(damage, speed, shape, position, direction, vertices * 100);
    }
}
