package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import model.weapon.Weapon;
import view.interfaces.Displayer;

/**
 * Created by Tomas on 10/23/17.
 */
public class WeaponDisplayer extends Displayer<Weapon> {

    public WeaponDisplayer(@NotNull Main gameFramework) {
        super(gameFramework);
    }

    @Override
    public void display(@NotNull Weapon displayer) {
        gameFramework.image(gameFramework.loadImage(''), 30, 30);
    }

}
