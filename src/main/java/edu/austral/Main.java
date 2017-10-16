package edu.austral;

import controller.GameController;
import edu.austral.util.Vector2;
import model.SpaceShip;
import model.interfaces.Model;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends GameFramework {

    private final GameController gameController;

    public Main() {
        // SpaceShips creation
        final List<SpaceShip> players = new ArrayList<>();
        players.add(new SpaceShip(new Vector2(width/4, height/2)));
        players.add(new SpaceShip(new Vector2((width/4) * 3, height/2)));
        gameController = new GameController(players);
    }

    public static void main(String args[]) {
        PApplet.main("edu.austral.Main");
    }

    @Override public void draw(float time, PApplet graphics) {
        gameController.draw(time, graphics);
    }

    @Override public void keyPressed(KeyEvent event) {
        gameController.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        gameController.keyReleased(event);
    }
}
