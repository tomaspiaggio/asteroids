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
        final Option<Model> spaceship = options[1];
        if(((SpaceShip) spaceship.getValue()) != this.spaceship) {
            this.spaceship.incrementScore(bullet.getValue().getScore());
            ((SpaceShip) spaceship).decrementLife(bullet.getValue().getDamage());
            bullet.getValue().decrementDamage(bullet.getValue().getDamage());
        }
    }
}
