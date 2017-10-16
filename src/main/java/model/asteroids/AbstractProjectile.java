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

    private long damage;
    private long speed;
    private Shape shape;
    private Vector2 position;
    private Vector2 direction;

    public AbstractProjectile(long damage, long speed, Shape shape, Vector2 position, Vector2 direction) {
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

    public long getDamage() {
        return damage;
    }

    public long getSpeed() {
        return speed;
    }
}
