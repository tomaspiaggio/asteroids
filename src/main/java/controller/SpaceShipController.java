package controller;

import com.sun.istack.internal.NotNull;
import model.spaceship.SpaceShip;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 10/15/17.
 */
public class SpaceShipController {

    private List<SpaceShip> spaceShips;
    private Map<Integer, SpaceShipActionTuple> playerKey;
    private Map<Integer, SpaceShipActionTuple> releasable;

    public SpaceShipController(@NotNull List<SpaceShip> spaceShips) {
        this.spaceShips = spaceShips;
        playerKey = new HashMap<>();
        releasable = new HashMap<>();
        // Player 1
        final SpaceShip player1 = spaceShips.get(0);
        playerKey.put(KeyEvent.VK_UP, new SpaceShipActionTuple(SpaceShipActions.FORWARD, player1));
        playerKey.put(KeyEvent.VK_LEFT, new SpaceShipActionTuple(SpaceShipActions.LEFT, player1));
        playerKey.put(KeyEvent.VK_RIGHT, new SpaceShipActionTuple(SpaceShipActions.RIGHT, player1));
        playerKey.put(KeyEvent.VK_SPACE, new SpaceShipActionTuple(SpaceShipActions.CHARGING, player1));
        releasable.put(KeyEvent.VK_SPACE, new SpaceShipActionTuple(SpaceShipActions.SHOOT, player1));

        // Player 2
        final SpaceShip player2 = spaceShips.get(1);
        playerKey.put(KeyEvent.VK_W, new SpaceShipActionTuple(SpaceShipActions.FORWARD, player2));
        playerKey.put(KeyEvent.VK_A, new SpaceShipActionTuple(SpaceShipActions.LEFT, player2));
        playerKey.put(KeyEvent.VK_D, new SpaceShipActionTuple(SpaceShipActions.RIGHT, player2));
        playerKey.put(KeyEvent.VK_TAB, new SpaceShipActionTuple(SpaceShipActions.CHARGING, player2));
        releasable.put(KeyEvent.VK_TAB, new SpaceShipActionTuple(SpaceShipActions.SHOOT, player2));
    }

    public void keyPressed(int event) {
        final SpaceShipActionTuple spaceShipActionTuple = playerKey.get(event);
        if(spaceShipActionTuple == null) return;
        spaceShipActionTuple.spaceShip.performAction(spaceShipActionTuple.action);
    }

    public void keyReleased(int event) {
        final SpaceShipActionTuple spaceShipActionTuple = releasable.get(event);
        if(spaceShipActionTuple == null) return;
        spaceShipActionTuple.spaceShip.performAction(spaceShipActionTuple.action);
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