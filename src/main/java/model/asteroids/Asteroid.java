package model.asteroids;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;

import java.awt.*;
import java.util.List;

/**
 * Created by Tomas on 10/10/17.
 */
public class Asteroid extends AbstractProjectile {

    private int life;
    private List<Vector2> points;

    public Asteroid(long damage, long speed, @NotNull Shape shape, @NotNull Vector2 position, @NotNull Vector2 direction, int life, @NotNull List<Vector2> points) {
        super(damage, speed, shape, position, direction);
        this.life = life;
        this.points = points;
    }

    @Override
    public void update(float deltaTime) {
        position = new Vector2(position.x() + (direction.x() * deltaTime/1000), position.y() + (direction.y() * deltaTime / 1000));
    }

    @Override
    public void collisionedWith(AbstractProjectile collisionable) {
        if(collisionable.getClass() == Bullet.class) life -= collisionable.getDamage();
    }

    public List<Vector2> getPoints() {
        return points;
    }
}
