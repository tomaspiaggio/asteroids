package view.interfaces;

import edu.austral.GameFramework;
import edu.austral.Main;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

/**
 * Created by Tomas on 10/15/17.
 */
public abstract class VisibleObject {

    protected final Main gameFramework;

    public VisibleObject(Main gameFramework) {
        this.gameFramework = gameFramework;
    }
}
