package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.projectiles.bullet.Bullet;
import view.interfaces.Displayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/15/17.
 */
public class BulletDisplayer extends Displayer<Bullet> {


    public BulletDisplayer(@NotNull Main gameFramework) {
        super(gameFramework);
    }

    @Override
    public void display(@NotNull Bullet bullet) {
        final Vector2 pos = bullet.getPosition();
        gameFramework.circle(pos.x(), pos.y(), bullet.getRadius());
    }
}
