package model;

import com.sun.istack.internal.NotNull;
import edu.austral.util.CollisionEngine;
import model.asteroids.Asteroid;
import model.asteroids.Bullet;
import model.builders.AsteroidBuilder;
import model.interfaces.Mappable;
import model.interfaces.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 10/10/17.
 */
public class Mapa implements Model {

    private final int xBound;
    private final int yBound;
    private final List<SpaceShip> spaceShips;
    private final List<Asteroid> asteroids;
    private final CollisionEngine colEngine;
    private final AsteroidBuilder asteroidBuilder;
    private Map<SpaceShip, List<Bullet>> spaceShipShots;
    private Map<SpaceShip, List<Bullet>> impacted;

    public Mapa(int xBound, int yBound, @NotNull List<SpaceShip> spaceShips) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.spaceShips = spaceShips;
        asteroidBuilder = new AsteroidBuilder(xBound, yBound);
        colEngine = new CollisionEngine();
        asteroids = new ArrayList<>();
        spaceShipShots = new HashMap<>();
        impacted = new HashMap<>();
    }


    @Override
    public void update(float deltaTime) {
        // chequar balas pasadas, asteroides muertos, y si alguno de los personajes se murio, game over gano tal

        // TODO: CAMBIAR AHORA LOS SHOOTS SON LOS QUE LE PEGARON A LA NAVE
        // TODO: TENGO QUE CHEQUEAR A QUIEN LE PEGO Y SUMARLE PUNTOS
        spaceShipShots.keySet()
                .parallelStream()
                .forEach(key -> colEngine.checkCollisions(key, spaceShipShots.get(key)));
        spaceShips.forEach(s -> spaceShipShots.get(s).addAll(s.getShooted()));
        if(Math.random() > 0.3) asteroids.add(asteroidBuilder.build());
    }

    public Map<SpaceShip, List<Bullet>> shotsBulletsImpacted() {
        final Map<SpaceShip, List<Bullet>> spaceShipShots = impacted;
        impacted = new HashMap<>();
        return spaceShipShots;
    }
}
