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

    public VisibleSpaceShip(@NotNull Main gameFramework) {
        super(gameFramework);
    }

    @Override
    public void display(@NotNull SpaceShip spaceShip) {
        final Vector2 pos = spaceShip.getPosition();
        final Vector2 dir = spaceShip.getDirection();
        gameFramework.circle(pos.x(), pos.y(), 40);
        gameFramework.fill(255, 0, 0);
        gameFramework.circle(pos.x() + (dir.x() * 25), pos.y() + (dir.y() * 25), 10);
        gameFramework.fill(255);
    }
}
