package model;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by Tomas on 10/10/17.
 */
public class Game {

    private final Mapa mapa;

    public Game(int width, int height, @NotNull List<SpaceShip> spaceShips) {
        this.mapa = new Mapa(width, height, spaceShips);
    }
}
