package edu.austral;

import com.sun.istack.internal.NotNull;
import controller.SpaceShipController;
import edu.austral.util.CollisionEngine;
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
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main extends GameFramework {

    private final SpaceShipController spaceShipController;
    private List<Model> models;
    private static final int width = 800;
    private static final int height = 600;
    private final List<SpaceShip> players;
    private final Map<Class<? extends Model>, Displayer> modelDisplayers;
    private final Map<Class<? extends Model>, Function<Mappable, Boolean>> modelOutsideMap;
    private final Map<Integer, Integer> keyEvents;
    private final AsteroidBuilder ab = new AsteroidBuilder(width, height);
    private final CollisionEngine<Model> collisionEngine;
    private final float asteroidTimeThreshHold = 1000;
    private float lastAsteroidTime = 0;
    private int currentKey = -1;

    public Main() {
        // Instantiations
        this.players = new ArrayList<>();
        this.modelOutsideMap = new HashMap<>();
        this.modelDisplayers = new HashMap<>();
        this.models = new ArrayList<>();
        this.keyEvents = new HashMap<>();
        this.collisionEngine = new CollisionEngine<>();

        // Initializing key events
        this.keyEvents.put(37, KeyEvent.VK_LEFT);
        this.keyEvents.put(38, KeyEvent.VK_UP);
        this.keyEvents.put(39, KeyEvent.VK_RIGHT);
        this.keyEvents.put(97, KeyEvent.VK_A);
        this.keyEvents.put(119, KeyEvent.VK_W);
        this.keyEvents.put(100, KeyEvent.VK_D);
        this.keyEvents.put(9, KeyEvent.VK_TAB);
        this.keyEvents.put(32, KeyEvent.VK_SPACE);

        // Initializing two players and adding them to Models list
        players.add(new SpaceShip(new Vector2(width/4, height/2)));
        players.add(new SpaceShip(new Vector2((width/4) * 3, height/2)));
        this.models.addAll(players);

        // Initializing spaceship controller with players
        this.spaceShipController = new SpaceShipController(players);

        // To be able to check for different models different ways of being outside the map
        this.modelOutsideMap.put(Asteroid.class, a -> {
            final Vector2 pos = a.getPosition();
            final float errorMargin = 15;
            return pos.x() < (width + errorMargin) && pos.x() > (0 - errorMargin) && pos.y() > (0 - errorMargin) && pos.y() < (height + errorMargin);
        });
        this.modelOutsideMap.put(Bullet.class, a -> {
            final Vector2 pos = a.getPosition();
            final float errorMargin = 15;
            return pos.x() < (width + errorMargin) && pos.x() > (0 - errorMargin) && pos.y() > (0 - errorMargin) && pos.y() < (height + errorMargin);
        });
        this.modelOutsideMap.put(SpaceShip.class, a -> {
            final Vector2 pos = a.getPosition();
            final Vector2 corrected = new Vector2(Math.max(Math.min(width, pos.x()), 0), Math.max(Math.min(height, pos.y()), 0));
            ((SpaceShip) a).correctPosition(corrected);
            return true;
        });

        // Each mappable has different ways of displaying itself
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
                .filter(e -> e.isAlive())
                .collect(Collectors.toList());

        //Getting shots from spaceships
        players.forEach(e -> models.addAll(e.getShots()));

        // Updating elements and displaying them
        models.forEach(e -> {
            e.update(time);
            modelDisplayers.get(e.getClass()).display(e);
        });

        collisionEngine.checkCollisions(models);

        // Spawning asteroids
        spawnAsteroids(time);

        // Checking keyPresses and keyReleases
        if(keyPressed) {
            if(currentKey == -1) currentKey = (keyEvent.getKey() != 65535)? keyEvent.getKey() : keyEvent.getKeyCode();
            keyPressed(currentKey);
        } else if(currentKey != -1) {
            keyReleased(currentKey);
            currentKey = -1;
        }
    }


    public void keyPressed(int event) {
        Integer parsedEvent = keyEvents.get(event);
        if(parsedEvent == null) return;
        spaceShipController.keyPressed(parsedEvent);
    }


    public void keyReleased(int event) {
        Integer parsedEvent = keyEvents.get(event);
        if(parsedEvent == null) return;
        spaceShipController.keyReleased(parsedEvent);
    }

    private boolean isWithinMap(@NotNull Mappable mappable) {
        return modelOutsideMap.get(mappable.getClass()).apply(mappable);
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
