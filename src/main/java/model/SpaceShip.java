package model;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Collisionable;
import edu.austral.util.Vector2;
import model.asteroids.AbstractProjectile;
import model.asteroids.Bullet;
import model.interfaces.Mappable;
import model.interfaces.Model;

import java.awt.*;

/**
 * Created by Tomas on 10/10/17.
 */
public class SpaceShip implements Model, Mappable, Collisionable<AbstractProjectile> {


    private Bullet type;
    private Shape shape;
    private Vector2 position;
    private double speed;

    public SpaceShip(@NotNull Bullet type, @NotNull Shape shape, Vector2 position, double speed) {
        this.type = type;
        this.shape = shape;
        this.position = position;
        this.speed = speed;
    }

    public Bullet shoot() {
        return new Bullet(type.getDamage(), type.getSpeed(), type.getShape(), position, /*DIRECTION*/); //con la misma direccion que la spaceship
    }

    public void move(@NotNull Vector2 direction) {
        // left o right porque siempre va a ir para adelante?
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void collisionedWith(AbstractProjectile collisionable) {

    }
}
