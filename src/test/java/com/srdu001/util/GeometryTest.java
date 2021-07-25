package com.srdu001.util;


import com.srdu001.sswf.util.Geometry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

public class GeometryTest {

    @Test
    void threePointsAreAligned() {
        Point2D a = new Point2D.Double(2, -1);
        Point2D b = new Point2D.Double(5.2, 0.6);
        Point2D c = new Point2D.Double(6, 1);
        Assertions.assertTrue(Geometry.areAligned(a, b, c));
    }

    @Test
    void threePointsAreNotAligned() {
        Point2D a = new Point2D.Double(25, -1);
        Point2D b = new Point2D.Double(5.2, 0.6);
        Point2D c = new Point2D.Double(6, 1);
        Assertions.assertFalse(Geometry.areAligned(a, b, c));
    }


    @Test
    void triangleArea() {
        Double actual = Geometry.triangleArea(
                new Point2D.Double(0, 1),
                new Point2D.Double(4, 3),
                new Point2D.Double(3, 4));
        Assertions.assertEquals(3, actual);
    }

    @Test
    void radialPosition() {
        Point2D actual = Geometry.radialPosition(500, 10);
        Point2D expected = new Point2D.Double(-143.01273980483683, -479.11100619116866);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void trianglePerimeter() {
        double actual = Geometry.trianglePerimeter(
                new Point2D.Double(-2, 0),
                new Point2D.Double(2, 0),
                new Point2D.Double(0, 3));
        Assertions.assertEquals(11.21110255092798, actual);
    }

    @Test
    void simplify() {
        Assertions.assertEquals(10.005, Geometry.simplify(10.004655, 1000d));
    }

}