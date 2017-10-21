package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.projectiles.asteroid.Asteroid;
import view.interfaces.Displayer;

import java.util.List;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleAsteroid extends Displayer<Asteroid> {

    public VisibleAsteroid(@NotNull Main gameFramework) {
        super(gameFramework);
    }

    @Override
    public void display(@NotNull Asteroid asteroid) {
        gameFramework.beginShape();
        final Vector2 pos = asteroid.getPosition();
        asteroid.getPoints().forEach(e -> gameFramework.vertex(e.x() + pos.x(), e.y() + pos.y()));
        gameFramework.endShape(Main.CLOSE);
    }
}
