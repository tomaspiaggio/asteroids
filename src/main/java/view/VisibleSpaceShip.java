package view;

import com.sun.istack.internal.NotNull;
import edu.austral.Main;
import edu.austral.util.Vector2;
import model.spaceship.SpaceShip;
import view.interfaces.Displayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomas on 10/15/17.
 */
public class VisibleSpaceShip extends Displayer<SpaceShip> {

//    private final List<Vector2> baseTriangle;

    public VisibleSpaceShip(@NotNull Main gameFramework) {
        super(gameFramework);
//        this.baseTriangle = new ArrayList<>();
//        this.baseTriangle.add(new Vector2(-7.5f, 0));
//        this.baseTriangle.add(new Vector2(0, 12.9903810568f));
//        this.baseTriangle.add(new Vector2(0, 7.5f));
    }

    @Override
    public void display(@NotNull SpaceShip spaceShip) {
        final Vector2 pos = spaceShip.getPosition();
        final Vector2 dir = spaceShip.getDirection();
        gameFramework.circle(pos.x(), pos.y(), 40);
        gameFramework.fill(255, 0, 0);
        gameFramework.circle(pos.x() + (dir.x() * 25), pos.y() + (dir.y() * 25), 10);
        gameFramework.fill(255);
//        final List<Vector2> triangle = this.baseTriangle
//                .stream()
//                .map(e -> new Vector2(e.x() + pos.x(), e.y() + pos.y()))
//                .collect(Collectors.toList());
//        System.out.println(triangle);
//        final Vector2 current = triangle.get(2);
//        gameFramework.pushMatrix();
//        gameFramework.translate(current.x(), current.y() / 2);
//        gameFramework.triangle(triangle.get(0).x(), triangle.get(0).y(), triangle.get(1).x(), triangle.get(1).y(), triangle.get(2).x(), triangle.get(2).y());
//        gameFramework.rotate(dir.angle());
//        gameFramework.popMatrix();
    }
}
