package model.spaceship;

import model.interfaces.Model;
import model.projectiles.asteroid.Asteroid;
import util.Action;
import util.Option;

/**
 * Created by Tomas on 10/21/17.
 */
public class SpaceShipActionAsteroid implements Action {

    @Override
    public void performAction(Option... options) {
        final Option<SpaceShip> spaceship = options[0];
        final Option<Model> asteroid = options[1];
        spaceship.getValue().decrementLife(((Asteroid) asteroid.getValue()).getDamage());
        ((Asteroid) asteroid.getValue()).decrementLife(((Asteroid) asteroid.getValue()).getLife());
    }


}
