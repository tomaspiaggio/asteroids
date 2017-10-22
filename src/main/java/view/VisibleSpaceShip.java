package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.spaceship.SpaceShip;
import view.interfaces.Displayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleSpaceShip extends Displayer<SpaceShip> {

    private final List<Vector2> triangle;

    public VisibleSpaceShip(@NotNull Main gameFramework) {
        super(gameFramework);
        triangle = new ArrayList<>();
        triangle.add(new Vector2(0, 0));
        triangle.add(new Vector2(0, 15));
        triangle.add(new Vector2(7.5f, 12.9903810568f));
    }

    @Override
    public void display(@NotNull SpaceShip spaceShip) {
        final Vector2 pos = spaceShip.getPosition();
        final Vector2 dir = spaceShip.getDirection();
        final List<Vector2> triangle = this.triangle
                .stream()
                .map(e -> new Vector2(e.x() + pos.x(), e.y() + pos.y()))
                .collect(Collectors.toList());
        final Vector2 current = triangle.get(2);
        gameFramework.pushMatrix();
        gameFramework.translate(current.x(), current.y() / 2);
        gameFramework.triangle(triangle.get(0).x(), triangle.get(0).y(), triangle.get(1).x(), triangle.get(1).y(), triangle.get(2).x(), triangle.get(2).y());
        // TODO: ROTATE TRIANGLE CHECK IF ANGLE IS DEG OR RAD
        dir.angle();
        gameFramework.popMatrix();
    }
}
