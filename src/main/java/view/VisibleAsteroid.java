package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.asteroids.Asteroid;
import model.builders.AsteroidBuilder;
import view.interfaces.VisibleObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleAsteroid extends VisibleObject {

    final AsteroidBuilder asteroidBuilder;
    final Map<Asteroid, Vector2> asteroids;

    public VisibleAsteroid(int width, int height, @NotNull Main gameFramework) {
        super(gameFramework);
        this.asteroidBuilder = new AsteroidBuilder(width, height);
        this.asteroids = new HashMap<>();
    }

    public void newAsteroid(@NotNull Asteroid asteroid, int x, int y) {
        asteroids.put(asteroid, new Vector2(x, y));
    }

    public void displayAsteroids() {
        gameFramework.beginShape();
        asteroids.keySet().forEach(a -> a.getPoints().forEach(e -> {
            final Vector2 vec = asteroids.get(a);
            gameFramework.vertex(vec.x() + e.x(), vec.y() + e.y());
        }));
        gameFramework.endShape(Main.CLOSE);
    }
}
