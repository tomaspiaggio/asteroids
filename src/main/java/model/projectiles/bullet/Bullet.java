package model.projectiles.bullet;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;
import model.spaceship.SpaceShip;
import model.interfaces.Model;
import model.projectiles.AbstractProjectile;
import model.projectiles.asteroid.Asteroid;
import util.Action;

import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomas on 10/10/17.
 */
public class Bullet extends AbstractProjectile {

    private float radius;
    private final BulletActionSpaceShip bulletAction;
    private final Map<Class<? extends Model>, Action> collisionables;
    private double score;
    private long damage;
    private final long speed;

    public Bullet(long damage,
                  long speed,
                  @NotNull Vector2 position,
                  @NotNull Vector2 direction,
                  float radius,
                  @NotNull BulletActionSpaceShip bulletAction,
                  double score) {
        super(damage, speed, new Ellipse2D.Double(position.x(), position.y(), radius, radius), position, direction);
        this.radius = radius;
        this.bulletAction = bulletAction;
        this.damage = damage;
        this.speed = speed;
        this.collisionables = new HashMap<>();
        collisionables.put(SpaceShip.class, bulletAction);
        collisionables.put(Asteroid.class, new BulletActionAsteroid());
    }

    @Override
    public void update(float deltaTime) {
        position = new Vector2(position.x() + (direction.x() * deltaTime / 1000), position.y() + (direction.y() * deltaTime / 1000));
    }

    @Override
    public void collisionedWith(@NotNull Model collisionable) {
        collisionables.get(collisionable.getClass()).performAction(() -> this, () -> collisionable);
    }

    public void decrementDamage(long damage) {
        this.damage -= damage;
    }

    @Override
    public boolean isAlive() {
        return damage > 0;
    }

    public double getScore() {
        return score;
    }

    public float getRadius() {
        return radius;
    }
}
