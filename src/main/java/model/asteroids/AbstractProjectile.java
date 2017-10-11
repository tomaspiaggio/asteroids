package model.asteroids;

import edu.austral.util.Collisionable;
import edu.austral.util.Vector2;
import model.interfaces.Mappable;
import model.interfaces.Model;

import java.awt.*;

/**
 * Created by Tomas on 10/10/17.
 */
public abstract class AbstractProjectile implements Mappable, Model, Collisionable<AbstractProjectile> {

    private int damage;
    private double speed;
    private Shape shape;
    private Vector2 position;
    private Vector2 direction;

    public AbstractProjectile(int damage, double speed, Shape shape, Vector2 position, Vector2 direction) {
        this.damage = damage;
        this.speed = speed;
        this.shape = shape;
        this.position = position;
        this.direction = direction;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }
}
