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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Tomas on 10/10/17.
 */
public class SpaceShip implements Model, Mappable, Collisionable<Model> {

    private Shape shape;
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
    private List<Bullet> shooted;

    public SpaceShip(@NotNull Vector2 position) {
        this.position = position;
        this.spaceShipActions = new HashMap<>();
        this.collisionables = new HashMap<>();
        this.collisionables.put(Bullet.class, new SpaceShipActionBullet());
        this.collisionables.put(Bullet.class, new SpaceShipActionAsteroid());
        this.spaceShipActions.put(SpaceShipActions.LEFT, (pos, dir) -> direction.rotateDeg(-rotationAngle));
        this.spaceShipActions.put(SpaceShipActions.RIGHT, (pos, dir) -> direction.rotateDeg(rotationAngle));
        this.spaceShipActions.put(SpaceShipActions.FORWARD, (pos, dir) -> {
            pos.setX(pos.x() + dir.x());
            pos.setY(pos.y() + dir.y());
        });
        this.spaceShipActions.put(SpaceShipActions.CHARGING, (pos, dir) -> {
            if(charging == 0) charging = System.currentTimeMillis();
        });

        shooted = new ArrayList<>();
        bulletBuilder = new BulletBuilder();
        life = 1000;
        score = 0;
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

    public List<Bullet> getShooted() {
        final List<Bullet> aux = shooted;
        shooted = new ArrayList<>();
        return aux;
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }

    public void incrementScore(double score) {
        this.score += score;
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

    @Override
    public void collisionedWith(@NotNull Model collisionable) {
        collisionables.get(collisionable.getClass()).performAction(() -> this, () -> collisionable);
    }
}
