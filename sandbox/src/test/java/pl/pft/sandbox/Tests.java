package pl.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Francis_Castle on 2017-05-29.
 */
public class Tests {

    @Test
    public void testPoint(){
        Point p1 = new Point(1,3);
        Point p2 = new Point(0,2);
        Assert.assertEquals(p1.distance(p2),1.4142135623730951);

        p1 = new Point(1, 3);
        p2 = new Point(2, 3);
        Assert.assertEquals(p1.distance(p2),1.0);

        p1 = new Point(1, 4);
        p2 = new Point(3, 2);
        Assert.assertEquals(p1.distance(p2),2.8284271247461903);

        p1 = new Point(5, 5);
        p2 = new Point(9, 2);
        Assert.assertEquals(p1.distance(p2),5.0);

        p1 = new Point(3, 5);
        p2 = new Point(11, 11);
        Assert.assertEquals(p1.distance(p2),10.0);
    }
}
