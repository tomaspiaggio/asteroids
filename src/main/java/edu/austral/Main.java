package edu.austral;

import com.sun.istack.internal.NotNull;
import controller.GameController;
import edu.austral.util.Vector2;
import model.interfaces.Mappable;
import model.projectiles.bullet.Bullet;
import model.spaceship.SpaceShip;
import model.projectiles.asteroid.Asteroid;
import model.builders.AsteroidBuilder;
import model.interfaces.Model;
import processing.core.PApplet;
import view.VisibleAsteroid;
import view.VisibleBullet;
import view.VisibleSpaceShip;
import view.interfaces.Displayer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main extends GameFramework {

    private final GameController gameController;
    private List<Model> models;
    private static final int width = 800;
    private static final int height = 600;
    private final Map<Class<? extends Model>, Displayer> modelDisplayers;
    private final AsteroidBuilder ab = new AsteroidBuilder(width, height);
    private final float asteroidTimeThreshHold = 1000;
    private float lastAsteroidTime = 0;

    public Main() {
        final List<SpaceShip> players = new ArrayList<>();
        models = new ArrayList<>();
        players.add(new SpaceShip(new Vector2(width/4, height/2)));
        players.add(new SpaceShip(new Vector2((width/4) * 3, height/2)));
        gameController = new GameController(players);
        this.modelDisplayers = new HashMap<>();
        this.modelDisplayers.put(Bullet.class, new VisibleBullet(this));
        this.modelDisplayers.put(Asteroid.class, new VisibleAsteroid(this));
        this.modelDisplayers.put(SpaceShip.class, new VisibleSpaceShip(this));
    }

    public static void main(String args[]) {
        PApplet.main("edu.austral.Main");
    }

    @Override public void draw(float time, PApplet graphics) {
        // Filtering outside map
        models = models.stream()
                .filter(e -> isWithinMap(((Mappable) e)))
                .collect(Collectors.toList());
        // Updating elements and displaying them
        models.forEach(e -> {
            e.update(time);
            modelDisplayers.get(e.getClass()).display(e);
        });
        // Spawning asteroids
//        spawnAsteroids(time);

        // TODO: CHECK COLLISIONS
    }

    @Override public void keyPressed(KeyEvent event) {
        gameController.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        gameController.keyReleased(event);
    }

    private boolean isWithinMap(@NotNull Mappable mappable) {
        final Vector2 pos = mappable.getPosition();
        final float errorMargin = 15;
        return pos.x() < (width + errorMargin) && pos.x() > (0 - errorMargin) && pos.y() > (0 - errorMargin) && pos.y() < (height + errorMargin);
    }

    private void spawnAsteroids(float time) {
        lastAsteroidTime += time;
        if(lastAsteroidTime >= asteroidTimeThreshHold) {
            newAsteroid((int) (Math.random() * 10));
            lastAsteroidTime = 0;
        }
    }

    private void newAsteroid(int vertices) {
        final Asteroid a = ab.setVertices(vertices).build();
        models.add(a);
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
