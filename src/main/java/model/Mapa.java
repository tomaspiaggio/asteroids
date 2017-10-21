package model;

import com.sun.istack.internal.NotNull;
import edu.austral.util.CollisionEngine;
import model.projectiles.asteroid.Asteroid;
import model.builders.AsteroidBuilder;
import model.spaceship.SpaceShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/10/17.
 */
public class Mapa {

    private final int xBound;
    private final int yBound;
    private final List<SpaceShip> spaceShips;
    private final List<Asteroid> asteroids;
    private final CollisionEngine colEngine;
    private final AsteroidBuilder asteroidBuilder;

    public Mapa(int xBound, int yBound, @NotNull List<SpaceShip> spaceShips) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.spaceShips = spaceShips;
        asteroidBuilder = new AsteroidBuilder(xBound, yBound);
        colEngine = new CollisionEngine();
        asteroids = new ArrayList<>();
    }


    public void update(float deltaTime) {
        // chequar balas pasadas, asteroides muertos, y si alguno de los personajes se murio, game over gano tal

        // TODO: CAMBIAR AHORA LOS SHOOTS SON LOS QUE LE PEGARON A LA NAVE
        // TODO: TENGO QUE CHEQUEAR A QUIEN LE PEGO Y SUMARLE PUNTOS
//        spaceShipShots.keySet()
//                .parallelStream()
//                .forEach(key -> colEngine.checkCollisions(key, spaceShipShots.get(key)));
//        spaceShips.forEach(s -> spaceShipShots.get(s).addAll(s.getShooted()));
//        if(Math.random() > 0.3) asteroids.add(asteroidBuilder.build());
    }
}
