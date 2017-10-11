package model;

import model.asteroids.Asteroid;
import model.asteroids.Bullet;
import model.interfaces.Mappable;
import model.interfaces.Model;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Tomas on 10/10/17.
 */
public class Mapa implements Model {

    private int xBound;
    private int yBound;
    private Collection<Mappable> entities;
    private Collection<Asteroid> asteroids;
    private Map<SpaceShip, Collection<Bullet>> spaceShipShots;
    private Map<SpaceShip, Collection<Bullet>> impacted;


    @Override
    public void update(float deltaTime) {

    }

    public Map<SpaceShip, Collection<Bullet>> shotsBulletsImpacted() {
        final Map<SpaceShip, Collection<Bullet>> spaceShipShots = impacted;
        impacted = null;
        return impacted;
    }
}
