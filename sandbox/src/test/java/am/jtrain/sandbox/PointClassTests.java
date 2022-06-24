package am.jtrain.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointClassTests {

    @Test
    public void testDistCorrect1(){
        Point p1 = new Point(0,3.0);
        Point p2 = new Point(0, 0);

        Assert.assertEquals(Point.distance(p1, p2), 3);
    }

    @Test
    public void testDistCorrect2(){
        Point p1 = new Point(-3.5,0);
        Point p2 = new Point(4, 18);

        Assert.assertNotEquals(Point.distance(p1, p2), 20);
    }


}
