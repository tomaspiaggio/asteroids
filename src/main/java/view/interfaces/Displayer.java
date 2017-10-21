package view.interfaces;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import model.interfaces.Model;

/**
 * Created by Tomas on 10/15/17.
 */
public abstract class Displayer<T extends Model> {

    protected final Main gameFramework;

    public Displayer(Main gameFramework) {
        this.gameFramework = gameFramework;
    }

    public abstract void display(@NotNull T displayer);
}
