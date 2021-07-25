package com.srdu001.sswf.util;

import java.awt.geom.Point2D;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class Geometry {

    public static Double triangleArea(Point2D a, Point2D b, Point2D c) {
        return Math.abs((a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY()) + c.getX() * (a.getY() - b.getY())) / 2);
    }

    public static boolean areAligned(Point2D a, Point2D b, Point2D c) {

        Point2D abv = new Point2D.Double(simplify(b.getX(), 10d) - simplify(a.getX(), 10d),
                simplify(b.getY(), 10d) - simplify(a.getY(), 10d));
        Point2D bcv = new Point2D.Double(simplify(c.getX(), 10d) - simplify(b.getX(),10d),
                simplify(c.getY(), 10d) - simplify(b.getY(), 10d));
        return (simplify(abv.getX() / bcv.getX(), 10d).doubleValue()
                == simplify(abv.getY() / bcv.getY(), 10d).doubleValue());
    }

    public static Point2D radialPosition(Integer radius, Integer degrees) {
        double x = radius * cos(toRadians(degrees) - 90);
        double y = radius * sin(toRadians(degrees) - 90);

        return new Point2D.Double(x, y);
    }

    public static Double simplify(Double number, Double decimals) {
        return (double) Math.round(number * decimals) / decimals;
    }

    public static Boolean isInsideTriangle(Point2D a, Point2D b, Point2D c, Point2D p) {
        Double original = triangleArea(a, b, c);
        Double a1 = triangleArea(p, b, c);
        Double a2 = triangleArea(a, p, c);
        Double a3 = triangleArea(a, b, p);
        return simplify(original, 1000d).doubleValue() == simplify(a1 + a2 + a3, 1000d).doubleValue();
    }

    public static Double trianglePerimeter(Point2D a, Point2D b, Point2D c) {
        return a.distance(b) + b.distance(c) + c.distance(a);
    }
}
