import model.builders.AsteroidBuilder;
import model.geommetrics.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/11/17.
 */
public class TestAsteroidBuilder {

//    @Test
//    public void testAsteroidBulider() {
//        final AsteroidBuilder ab = new AsteroidBuilder();
//        final List<Point2D> points = ab.polygonVertices(5, 5);
//        System.out.println(points);
//    }
//
//    @Test
//    public void testNormalizePoints() {
//        final AsteroidBuilder ab = new AsteroidBuilder();
//        final List<Point2D> points = ab.polygonVertices(5, 5);
//        final List<Point2D> normalizedPoints = ab.normalizePoints(points);
//        System.out.println(normalizedPoints);
//    }
//
//    @Test
//    public void testRandomInCircle() {
//        final AsteroidBuilder ab = new AsteroidBuilder();
//        final List<Point2D> pointsWithinCircle = new ArrayList<>();
//        final List<Point2D> points = ab.polygonVertices(5, 5);
//        final List<Point2D> normalizedPoints = ab.normalizePoints(points);
//        for (int i = 0; i < 5; i++) {
//            Point2D point = normalizedPoints.get(i);
//            point.sum(ab.randomPointWithinCircle(2.5));
//            pointsWithinCircle.add(point);
//        }
//        System.out.println(pointsWithinCircle);
//    }
}
