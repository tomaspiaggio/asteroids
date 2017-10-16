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
        time = Math.min(time, 1000); // max time charging bullet = 1 second
        bullet = new Bullet(time/10, 1000/time, position, direction, time/10);
        return this;
    }

    @Override
    public Bullet build() {
        return bullet;
    }
}
