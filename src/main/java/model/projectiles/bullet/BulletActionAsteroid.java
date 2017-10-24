package model.projectiles.bullet;

import com.sun.istack.internal.NotNull;
import model.interfaces.Model;
import model.projectiles.asteroid.Asteroid;
import model.spaceship.SpaceShip;
import util.Action;
import util.Option;

/**
 * Created by Tomas on 10/19/17.
 */
public class BulletActionAsteroid implements Action {

    private SpaceShip spaceShip;

    public BulletActionAsteroid(@NotNull SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    @Override
    public void performAction(Option... options) {
        final Option<Bullet> bullet = options[0];
        final Option<Model> asteroid = options[1];
        final long damage = bullet.getValue().getDamage();
        final long asteroidLife = ((Asteroid) asteroid.getValue()).getLife();
        if(damage > asteroidLife) {
            ((Asteroid) asteroid.getValue()).decrementLife(asteroidLife);
            bullet.getValue().decrementDamage(asteroidLife);
        } else {
            ((Asteroid) asteroid.getValue()).decrementLife(damage);
            bullet.getValue().decrementDamage(damage);
        }
        spaceShip.incrementScore((double)options[2].getValue());
    }
}
