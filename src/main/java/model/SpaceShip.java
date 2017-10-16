package model;

import com.sun.istack.internal.NotNull;
import controller.SpaceShipActions;
import edu.austral.util.Collisionable;
import edu.austral.util.Vector2;
import model.asteroids.AbstractProjectile;
import model.asteroids.Bullet;
import model.builders.BulletBuilder;
import model.interfaces.Mappable;
import model.interfaces.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/10/17.
 */
public class SpaceShip implements Model, Mappable, Collisionable<AbstractProjectile> {

    private Shape shape;
    private Vector2 position;
    private Vector2 direction;
    private int life;
    private long charging;
    private final double speed = 5;
    private final float rotationAngle = 5;
    private final BulletBuilder bulletBuilder;

    private List<Bullet> shooted;

    public SpaceShip(@NotNull Vector2 position) {
        this.position = position;
        shooted = new ArrayList<>();
        bulletBuilder = new BulletBuilder();
        life = 1000;
        // TODO: Ver como definir la shape (processing triangle)
    }

    public void performAction(@NotNull SpaceShipActions action) {
        // Sabe para donde se tiene que mover acorde a lo que le pasan
        switch (action) {
            case LEFT:
                // rotate left
                direction.rotateDeg(-rotationAngle);
                break;
            case RIGHT:
                // rotate right
                direction.rotateDeg(rotationAngle);
                break;
            case FORWARD:
                // go forward
                position.setX(position.x() + direction.x());
                position.setY(position.y() + direction.y());
                break;
            case CHARGING:
                if(charging != 0)
                    charging = System.currentTimeMillis();
                break;
        }
    }

    public Bullet shoot() {
        long timePressed = Math.min(charging, 1000); // max time charging bullet = 1 second
        charging = 0;
        return bulletBuilder
                .setChargeTime(timePressed, position, direction)
                .build();
    }

    @Override
    public void update(float deltaTime) {

    }

    public List<Bullet> getShooted() {
        final List<Bullet> aux = shooted;
        shooted = new ArrayList<>();
        return aux;
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
        life -= collisionable.getDamage();
        if(collisionable.getClass() == Bullet.class)
            shooted.add((Bullet)collisionable);
    }
}
