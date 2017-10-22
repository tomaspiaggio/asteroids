package model.spaceship;

import com.sun.istack.internal.NotNull;
import controller.SpaceShipActions;
import edu.austral.util.Collisionable;
import edu.austral.util.Vector2;
import model.projectiles.AbstractProjectile;
import model.projectiles.bullet.Bullet;
import model.builders.BulletBuilder;
import model.interfaces.Mappable;
import model.interfaces.Model;
import util.Action;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Tomas on 10/10/17.
 */
public class SpaceShip implements Model, Mappable, Collisionable<Model> {

    private final Shape shape;
    private Vector2 position;
    private Vector2 direction;
    private long life;
    private double score;
    private long charging;
    private final double speed = 5;
    private final float rotationAngle = 5;
    private final BulletBuilder bulletBuilder;
    private final Map<SpaceShipActions, BiConsumer<Vector2, Vector2>> spaceShipActions;
    private final Map<Class<? extends Model>, Action> collisionables;

    public SpaceShip(@NotNull Vector2 position) {
        // Initializations
        this.spaceShipActions = new HashMap<>();
        this.collisionables = new HashMap<>();
        this.shape = new Ellipse2D.Double(position.x() - 10, position.y() - 10, 20, 20);
        this.bulletBuilder = new BulletBuilder();
        this.direction = new Vector2(0, 1);
        this.position = position;
        this.life = 1000000;
        this.score = 0;

        // Collisionable actions
        this.collisionables.put(Bullet.class, new SpaceShipActionBullet());
        this.collisionables.put(Bullet.class, new SpaceShipActionAsteroid());

        // Setting SpaceShip Actions
        this.spaceShipActions.put(SpaceShipActions.LEFT, (pos, dir) -> direction.rotateDeg(-rotationAngle));
        this.spaceShipActions.put(SpaceShipActions.RIGHT, (pos, dir) -> direction.rotateDeg(rotationAngle));
        this.spaceShipActions.put(SpaceShipActions.CHARGING, (pos, dir) -> { if(charging == 0) charging = System.currentTimeMillis(); });
        this.spaceShipActions.put(SpaceShipActions.SHOOT, (pos, dir) -> shoot());
        this.spaceShipActions.put(SpaceShipActions.FORWARD, (pos, dir) -> {
            pos.setX(pos.x() + dir.x());
            pos.setY(pos.y() + dir.y());
        });
    }

    public void performAction(@NotNull SpaceShipActions action) {
        spaceShipActions
                .get(action)
                .accept(position, direction);
    }

    public Bullet shoot() {
        long timePressed = System.currentTimeMillis() - charging;
        charging = 0;
        return bulletBuilder
                .setChargeTime(timePressed, position, direction, this)
                .build();
    }

    @Override
    public void update(float deltaTime) {

    }

    public void correctPosition(@NotNull Vector2 position) {
        this.position = position;
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }

    public void incrementScore(double score) {
        this.score += score;
        System.out.println(this.score);
    }

    public void decrementLife(long life) {
        this.life -= life;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    @Override
    public void collisionedWith(@NotNull Model collisionable) {
        collisionables.get(collisionable.getClass()).performAction(() -> this, () -> collisionable);
    }
}
