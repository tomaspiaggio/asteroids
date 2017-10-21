package controller;

import com.sun.istack.internal.NotNull;
import edu.austral.GameFramework;
import edu.austral.util.CollisionEngine;
import model.Game;
import model.spaceship.SpaceShip;
import model.interfaces.Model;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/15/17.
 */
public class GameController extends GameFramework {

    private final SpaceShipController spaceShipController;
    private final Game game;
    private final List<Model> model;
    private final CollisionEngine collisionEngine;
    static final int CLOSE = GameFramework.CLOSE;

    public GameController(@NotNull List<SpaceShip> spaceShips) {
        this.model = new ArrayList<>(spaceShips);
        this.spaceShipController = new SpaceShipController(spaceShips);
        this.game = new Game(width, height, spaceShips);
        this.collisionEngine = new CollisionEngine();
    }

    @Override
    public void draw(float time, PApplet graphics) {
        // update models according to controllers input and draw them
//        model.forEach(e -> e.update(time));
    }

    @Override
    public void keyPressed(KeyEvent event) {
        spaceShipController.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) { spaceShipController.keyReleased(event); }

}
