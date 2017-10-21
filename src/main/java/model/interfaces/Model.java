package model.interfaces;

import edu.austral.util.Collisionable;

/**
 * Created by Tomas on 10/10/17.
 */
public interface Model extends Collisionable<Model> {

    public void update(float deltaTime);

    public boolean isAlive();

}
