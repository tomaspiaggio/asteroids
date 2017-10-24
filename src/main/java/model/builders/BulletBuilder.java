package model.builders;

import com.sun.istack.internal.NotNull;
import edu.austral.util.Vector2;
import model.projectiles.bullet.BulletActionAsteroid;
import model.spaceship.SpaceShip;
import model.projectiles.bullet.Bullet;
import model.projectiles.bullet.BulletActionSpaceShip;
import util.Builder;

/**
 * Created by Tomas on 10/15/17.
 */
public class BulletBuilder implements Builder<Bullet> {

    private Bullet bullet;

    public BulletBuilder setChargeTime(long time, @NotNull Vector2 position, @NotNull Vector2 direction, @NotNull SpaceShip spaceShip) {
        time = Math.min(time, 1000); // max time charging bullet = 1 second
        bullet = new Bullet(time, 1000/time, position, direction, time/10, new BulletActionSpaceShip(spaceShip), new BulletActionAsteroid(spaceShip), time);
        return this;
    }

    @Override
    public Bullet build() {
        return bullet;
    }
}
