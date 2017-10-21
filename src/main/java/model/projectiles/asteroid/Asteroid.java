package model.projectiles.asteroid;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;
import model.spaceship.SpaceShip;
import model.interfaces.Model;
import model.projectiles.AbstractProjectile;
import model.projectiles.bullet.Bullet;
import util.Action;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 10/10/17.
 */
public class Asteroid extends AbstractProjectile {

    private long life;
    private final List<Vector2> points;
    private final Map<Class<? extends Model>, Action> collisionables;

    public Asteroid(long damage,
                    long speed,
                    @NotNull Shape shape,
                    @NotNull Vector2 position,
                    @NotNull Vector2 direction,
                    long life,
                    @NotNull List<Vector2> points) {
        super(damage, speed, shape, position, direction);
        this.life = life;
        this.points = points;
        this.collisionables = new HashMap<>();
        collisionables.put(Bullet.class, new AsteroidActionBullet());
        collisionables.put(SpaceShip.class, new AsteroidActionSpaceShip());
    }

    @Override
    public void update(float deltaTime) {
        position = new Vector2(position.x() + (direction.x() * deltaTime/1000), position.y() + (direction.y() * deltaTime / 1000));
    }

    @Override
    public void collisionedWith(@NotNull Model collisionable) {
        collisionables.get(collisionable.getClass()).performAction(() -> this, () -> collisionable);
    }

    public List<Vector2> getPoints() {
        return points;
    }

    public long getLife() {
        return life;
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }

    public void decrementLife(long life) {
        this.life -= life;
    }
}
