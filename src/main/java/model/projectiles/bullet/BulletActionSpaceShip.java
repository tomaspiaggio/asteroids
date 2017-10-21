package model.projectiles.bullet;

import com.sun.istack.internal.NotNull;
import model.spaceship.SpaceShip;
import util.Action;
import util.Option;

/**
 * Created by Tomas on 10/17/17.
 */
public class BulletActionSpaceShip implements Action {

    private SpaceShip spaceship;

    public BulletActionSpaceShip(@NotNull SpaceShip spaceship) {
        this.spaceship = spaceship;
    }

    public void performAction(@NotNull Option... options) {
        final Option<Bullet> bullet = options[0];
        this.spaceship.incrementScore(bullet.getValue().getScore());
    }
}
