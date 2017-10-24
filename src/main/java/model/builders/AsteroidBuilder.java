package model.builders;

import edu.austral.util.Vector2;
import model.projectiles.asteroid.Asteroid;
import util.Builder;

import java.awt.*;
import java.awt.geom.Ellipse2D;
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
    private final List<Vector2> positions;
    private List<Vector2> points;

    public AsteroidBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        positions = new ArrayList<>();
        positions.add(new Vector2(0, height / 3));
        positions.add(new Vector2(0, height / 3 * 2));
        positions.add(new Vector2(width, height / 3 * 2));
        positions.add(new Vector2(width, height / 3 * 2));
        positions.add(new Vector2(width / 3, 0));
        positions.add(new Vector2(width / 3 * 2, 0));
        positions.add(new Vector2(width / 3, height));
        positions.add(new Vector2(width / 3 * 2, height));
    }

    private Vector2 randomPointWithinCircle(double radius) {
        final double rSquared = radius*radius;
        final double x = (Math.random() * (radius*2)) - radius;
        final double y = (((Math.random() * 2) - 1) * Math.sqrt(rSquared - x*x));
        return new Vector2((float)x, (float)y);
    }

    private List<Vector2> polygonVertices(int vertices, double radius) {
        final double polygonRadius = (2 * radius) / (2 * Math.sin(180 / vertices));
        final List<Vector2> points = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            final double x = polygonRadius * Math.cos(2 * Math.PI * i / vertices);
            final double y = polygonRadius * Math.sin(2 * Math.PI * i / vertices);
            points.add(new Vector2((float)x, (float)y));
        }
        return points;
    }

    private List<Vector2> normalizePoints(List<Vector2> points) {
        final List<Vector2> result = new ArrayList<>();
        Vector2 min = new Vector2(0, 0);
        for (Vector2 p: points) {
            if(p.x() < min.x()) min = min.setX(p.x());
            if(p.y() < min.y()) min = min.setY(p.y());
        }
        for(Vector2 p: points) result.add(new Vector2(p.x() - min.x(), p.y() - min.y()));
        return result;
    }

    private void makeShape() {
        final List<Vector2> pointsWithinCircle = new ArrayList<>();
        final List<Vector2> points = polygonVertices(vertices, 2 * radius);
        for (int i = 0; i < vertices; i++) {
            final Vector2 point = points.get(i);
            pointsWithinCircle.add(point.$plus(randomPointWithinCircle(radius)));
        }
        this.points = normalizePoints(pointsWithinCircle);
    }

    public AsteroidBuilder setVertices(int vertices) {
        if(vertices == 7) vertices = 6;
        if(vertices == 8) vertices = 9;
        this.vertices = Math.min(10, Math.max(4, vertices));
        this.radius = this.vertices * 2;
        this.damage = this.vertices * 100;
        this.speed = 50/this.vertices;
        makeShape();
        this.radius *= 5;
        return this;
    }

    @Override
    public Asteroid build() {
        final Vector2 position = positions.get((int)((positions.size() - 1) * Math.random()));
        Vector2 direction = new Vector2(0, speed);
        if(position.x() == width) direction = direction.rotateDeg((float) Math.random() * 180);
        else if (position.x() == 0) direction = direction.rotateDeg(-(float)(Math.random() * 180));
        else if(position.y() == height) direction = direction.rotateDeg(90 + (float)(Math.random() * 180));
        else direction = direction.rotateDeg(-90 + (float)(Math.random() * 180));
        return new Asteroid(damage, speed, new Ellipse2D.Double(position.x(), position.y(), this.radius, this.radius), position, direction, vertices * 30, this.points, this.radius);
    }
}
