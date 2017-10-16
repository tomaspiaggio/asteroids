package model.builders;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;
import model.asteroids.Asteroid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        this.vertices = Math.min(10, Math.max(3, vertices));
        this.radius = vertices * 2;
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
        return new Asteroid(damage, speed, shape, position, direction, vertices * 100, this.points);
    }
}
