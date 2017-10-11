package model.asteroids;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;

import java.awt.*;

/**
 * Created by Tomas on 10/10/17.
 */
public class Asteroid extends AbstractProjectile {

    public Asteroid(int damage, double speed, @NotNull Shape shape, @NotNull Vector2 position, @NotNull Vector2 direction) {
        super(damage, speed, shape, position, direction);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void collisionedWith(AbstractProjectile collisionable) {

    }

}
