package controller;

import com.sun.istack.internal.NotNull;
import model.spaceship.SpaceShip;
import model.projectiles.bullet.Bullet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 10/15/17.
 */
public class SpaceShipController {

    private List<SpaceShip> spaceShips;
    private Map<Character, SpaceShipActionTuple> playerKey;
    private Map<Character, SpaceShipActionTuple> releasable;
    private Map<SpaceShip, List<Bullet>> spaceShipBullet;

    public SpaceShipController(@NotNull List<SpaceShip> spaceShips) {
        this.spaceShips = spaceShips;
        spaceShipBullet = new HashMap<>();
        playerKey = new HashMap<>();
        releasable = new HashMap<>();
        // Player 1
        final SpaceShip player1 = spaceShips.get(0);
        playerKey.put('1', new SpaceShipActionTuple(SpaceShipActions.FORWARD, player1));
        playerKey.put('2', new SpaceShipActionTuple(SpaceShipActions.LEFT, player1));
        playerKey.put('3', new SpaceShipActionTuple(SpaceShipActions.RIGHT, player1));
        playerKey.put('4', new SpaceShipActionTuple(SpaceShipActions.CHARGING, player1));
        releasable.put('4', new SpaceShipActionTuple(SpaceShipActions.SHOOT, player1));

        // Player 2
        final SpaceShip player2 = spaceShips.get(1);
        playerKey.put('w', new SpaceShipActionTuple(SpaceShipActions.FORWARD, player2));
        playerKey.put('a', new SpaceShipActionTuple(SpaceShipActions.LEFT, player2));
        playerKey.put('d', new SpaceShipActionTuple(SpaceShipActions.RIGHT, player2));
        playerKey.put(' ', new SpaceShipActionTuple(SpaceShipActions.CHARGING, player2));
        releasable.put(' ', new SpaceShipActionTuple(SpaceShipActions.SHOOT, player2));
    }

    public void keyPressed(char event) {
        final SpaceShipActionTuple spaceShipActionTuple = playerKey.get(event);
        spaceShipActionTuple.spaceShip.performAction(spaceShipActionTuple.action);
        System.out.println(event);
    }

    public void keyReleased(char event) {
        final SpaceShipActionTuple spaceShipActionTuple = releasable.get(event);
        List<Bullet> bullets = spaceShipBullet.get(spaceShipActionTuple.spaceShip);
        if(bullets == null) bullets = new ArrayList<>();
        bullets.add(spaceShipActionTuple.spaceShip.shoot());
        spaceShipBullet.put(spaceShipActionTuple.spaceShip, bullets);
    }

    private class SpaceShipActionTuple {
        SpaceShipActions action;
        SpaceShip spaceShip;

        public SpaceShipActionTuple(SpaceShipActions action, SpaceShip spaceShip) {
            this.action = action;
            this.spaceShip = spaceShip;
        }
    }
}