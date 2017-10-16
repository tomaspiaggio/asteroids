package model.builders;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;
import model.asteroids.Bullet;

/**
 * Created by Tomas on 10/15/17.
 */
public class BulletBuilder implements Builder<Bullet> {

    private Bullet bullet;

    public BulletBuilder setChargeTime(long time, @NotNull Vector2 position, @NotNull Vector2 direction) {
        bullet = new Bullet(time/10, Math.min(100 + (1000/time), 1000), position, direction);
        return this;
    }

    @Override
    public Bullet build() {
        return bullet;
    }
}
