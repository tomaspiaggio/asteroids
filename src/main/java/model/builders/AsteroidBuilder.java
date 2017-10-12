package model.builders;

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
    private double speed;
    private Vector2 position;
    private Vector2 direction;
    private Shape shape;

    public AsteroidBuilder(int vertices, int radius, int damage, double speed, Vector2 position, Vector2 direction) {
        this.vertices = vertices;
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.direction = direction;
        makeShape();
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

    @Override
    public Asteroid build() {
        return new Asteroid(damage, speed, shape, position, direction);;
    }
}
