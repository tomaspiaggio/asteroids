package model.asteroids;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;

import java.awt.*;

/**
 * Created by Tomas on 10/10/17.
 */
public class Bullet extends AbstractProjectile {

    private float radius;

    public Bullet(long damage, long speed, @NotNull Vector2 position, @NotNull Vector2 direction, float radius) {
        super(damage, speed, /*new Ellpise*/ null, position, direction);
        this.radius = radius;
    }

    @Override
    public void update(float deltaTime) {
        // ir en la direccion dicha delta time para adelante
    }

    @Override
    public void collisionedWith(AbstractProjectile collisionable) {

    }

    public float getRadius() {
        return radius;
    }
}
