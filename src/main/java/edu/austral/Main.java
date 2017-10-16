package edu.austral;

import controller.GameController;
import edu.austral.util.Vector2;
import model.SpaceShip;
import model.asteroids.Asteroid;
import model.builders.AsteroidBuilder;
import model.interfaces.Model;
import processing.core.PApplet;
import view.VisibleAsteroid;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends GameFramework {

    private final GameController gameController;
    final VisibleAsteroid va = new VisibleAsteroid(this);
    final AsteroidBuilder ab = new AsteroidBuilder(width, height);
    final Asteroid a = ab.setVertices(5).build();

    public Main() {
        final List<SpaceShip> players = new ArrayList<>();
        players.add(new SpaceShip(new Vector2(width/4, height/2)));
        players.add(new SpaceShip(new Vector2((width/4) * 3, height/2)));
        gameController = new GameController(players);
        va.newAsteroid(a);
    }

    public static void main(String args[]) {
        PApplet.main("edu.austral.Main");
    }

    @Override public void draw(float time, PApplet graphics) {
//        gameController.draw(time, graphics);
        va.displayAsteroids();
        a.update(time);
    }

    @Override public void keyPressed(KeyEvent event) {
        gameController.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        gameController.keyReleased(event);
    }

    /**
     * Methods made public for Views not to be all inside this class
     */

    public void beginShape() {
        super.beginShape();
    }

    public void vertex(int x, int y) {
        super.vertex(x, y);
    }

    public void endShape(int option) { super.endShape(option); }

    public void ellipse(float x, float y, float width, float height) { super.ellipse(x, y, width, height); }

    public void circle(float x, float y, float radius) {
        ellipse(x, y, radius, radius);
    }
}
