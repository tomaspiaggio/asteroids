package edu.austral;

import processing.core.PApplet;

import java.awt.event.KeyEvent;

public abstract class GameFramework extends PApplet {

    @Override public void settings() {
        size(500, 500);
    }

    @Override public void setup() {
        clear();
    }

    @Override public void draw() {
        clear();
        draw((frameRate / 60) * 100, this);
    }


    public abstract void draw(float time, PApplet graphics);

    public abstract void keyPressed(KeyEvent event);

}
