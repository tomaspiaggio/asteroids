package model.spaceship;

import model.interfaces.Model;
import model.projectiles.bullet.Bullet;
import util.Action;
import util.Option;

/**
 * Created by Tomas on 10/21/17.
 */
public class SpaceShipActionBullet implements Action {

    @Override
    public void performAction(Option... options) {
        final Option<SpaceShip> spaceship = options[0];
        final Option<Model> bullet = options[1];
        spaceship.getValue().decrementLife(((Bullet) bullet.getValue()).getDamage());
        ((Bullet) bullet.getValue()).decrementDamage(((Bullet) bullet.getValue()).getDamage());
    }

}
