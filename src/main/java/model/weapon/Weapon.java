package model.weapon;

import com.sun.istack.internal.NotNull;
import model.interfaces.Model;
import util.Option;

/**
 * Created by Tomas on 10/17/17.
 */
public class Weapon implements Model {

    private final WeaponAction weaponAction;
    private long time;

    public Weapon(@NotNull WeaponAction weaponAction) {
        this.weaponAction = weaponAction;
        time = 0;
    }

    public void hold() {
        time = System.currentTimeMillis();
    }

    public void endHold() {
        weaponAction.performAction(new Option<Long>() {
            @Override
            public Long getValue() {
                return System.currentTimeMillis() - time;
            }
        });
    }

}
