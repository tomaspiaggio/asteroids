package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.asteroids.Bullet;
import view.interfaces.VisibleObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleBullet extends VisibleObject {

    private final List<Bullet> bullets;

    public VisibleBullet(@NotNull Main gameFramework) {
        super(gameFramework);
        bullets = new ArrayList<>();
    }

    public void displayBullets() {
        bullets.forEach(e -> {
            final Vector2 pos = e.getPosition();
            gameFramework.circle(pos.x(), pos.y(), e.getRadius());
        });
    }

    public void newBullet(@NotNull Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(@NotNull Bullet bullet) {
        bullets.remove(bullet);
    }
}
