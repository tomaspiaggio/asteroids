package model.asteroids;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;

import java.awt.*;

/**
 * Created by Tomas on 10/10/17.
 */
public class Asteroid extends AbstractProjectile {

    private int life;

    public Asteroid(long damage, long speed, @NotNull Shape shape, @NotNull Vector2 position, @NotNull Vector2 direction, int life) {
        super(damage, speed, shape, position, direction);
        this.life = life;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void collisionedWith(AbstractProjectile collisionable) {
        if(collisionable.getClass() == Bullet.class) life -= collisionable.getDamage();
    }

}
