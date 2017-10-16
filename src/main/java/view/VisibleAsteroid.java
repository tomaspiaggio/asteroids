package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.asteroids.Asteroid;
import model.builders.AsteroidBuilder;
import view.interfaces.VisibleObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleAsteroid extends VisibleObject {

    final List<Asteroid> asteroids;

    public VisibleAsteroid(@NotNull Main gameFramework) {
        super(gameFramework);
        this.asteroids = new ArrayList<>();
    }

    public void newAsteroid(@NotNull Asteroid asteroid) {
        asteroids.add(asteroid);
    }

    public void displayAsteroids() {
        gameFramework.beginShape();
        asteroids.forEach(a -> {
            final Vector2 pos = a.getPosition();
            a.getPoints().forEach(e -> gameFramework.vertex(e.x() + pos.x(), e.y() + pos.y()));
        });
        gameFramework.endShape(Main.CLOSE);
    }

    public void destroyAsteroid(@NotNull Asteroid asteroid) {
        asteroids.remove(asteroid);
    }
}
