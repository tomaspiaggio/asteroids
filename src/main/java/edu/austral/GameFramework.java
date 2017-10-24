package edu.austral;

import processing.core.PApplet;

public abstract class GameFramework extends PApplet {

    @Override public void settings() {
        size(800, 600);
    }

    @Override public void setup() {
        clear();
    }

    @Override public void draw() {
        clear();
        draw((frameRate / 60) * 100, this);
    }


    public abstract void draw(float time, PApplet graphics);

}
