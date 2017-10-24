package model.projectiles.bullet;

import com.sun.istack.internal.NotNull;
import model.interfaces.Model;
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
        final Option<SpaceShip> spaceship = options[1];
        if((spaceship.getValue()) != this.spaceship) {
            this.spaceship.incrementScore(bullet.getValue().getScore());
            spaceship.getValue().decrementLife(bullet.getValue().getDamage());
            bullet.getValue().decrementDamage(bullet.getValue().getDamage());
        }
    }
}
