package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import model.spaceship.SpaceShip;
import view.interfaces.Displayer;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleSpaceShip extends Displayer<SpaceShip> {

    public VisibleSpaceShip(Main gameFramework) {
        super(gameFramework);
    }

    @Override
    public void display(@NotNull SpaceShip displayer) {

    }
}
