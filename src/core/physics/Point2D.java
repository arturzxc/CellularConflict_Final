/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.physics;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Artur Krzynowek
 */
public class Point2D {

    public float x;
    public float y;

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2D copy() {
        return new Point2D(this.x, this.y);
    }

    /**
     * Returns a point that is mirror to the passed point in regards to this
     * point.
     *
     * @param p
     * @return
     */
    public Point2D mirrorPoint(Point2D p) {
        float dx = x - p.x;
        float dy = y - p.y;
        return new Point2D(x + dx, y + dy);
    }

    /**
     * Add velocity vector to the point
     */
    public Point2D addVelocity(Vector2D velocity) {
        this.x += velocity.x;
        this.y += velocity.y;
        return this;
    }

    /**
     * changes x and y values to be random between -1 and 1
     */
    public void randomise() {
        Random gen = new Random();
        x = gen.nextFloat() * 2 - 1;
        y = gen.nextFloat() * 2 - 1;
    }

    /**
     * Subtract velocity vector to the point
     */
    public Point2D subtractVelocity(Vector2D velocity) {
        this.x -= velocity.x;
        this.y -= velocity.y;
        return this;
    }

    /**
     * Calculate vector between two points
     */
    public Vector2D vectorTo(Point2D p) {
        Vector2D vec = new Vector2D(0, 0);
        vec.x = p.x - x;
        vec.y = p.y - y;
        return vec;
    }

    /**
     * Calculate distance between two point. This method uses Pythagoras
     * theorem.
     */
    public static float distance(Point2D point1, Point2D point2) {
        if (point1 == null || point2 == null) {
            return 0.0f;
        }
        float dx = point2.x - point1.x;
        float dy = point2.y - point1.y;
        float result = (float) Math.sqrt((dx * dx) + (dy * dy));
        return result;
    }

    /**
     * Given velocity, point p1 and point p2 calculate the third point which is
     * between p1 and p2 at a given distance determined by the velocity.
     */
    public static Point2D locationBetween(float separationDistance, Point2D p1, Point2D p2) {
        float distance = distance(p1, p2);
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        float x2 = dx * separationDistance / distance;
        float y2 = dy * separationDistance / distance;
        return new Point2D(p1.x + x2, p1.y + y2);
    }

    @Override
    public String toString() {
        DecimalFormat myFormatter = new DecimalFormat("#.0");
        return "x: " + myFormatter.format(x) + "\t y: " + myFormatter.format(y);
    }

    /**
     * returns a point that is a copy of this one
     * with added velocity
     * @param vec
     * @return 
     */
    public Point2D nextPoint(Vector2D vec) {
        Point2D p2 = this.copy();
        p2.addVelocity(vec);
        return p2;
    }

    /**
     * Using Float.compare method. If x and y of both points are
     * the same then they are equal.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        Point2D p = (Point2D) o;
        return (Float.compare(x, p.x) == 0 && Float.compare(y, p.y) == 0);
    }

    /**
    * Test main
    */
    public static void main(String[] str) {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = p1.mirrorPoint(p2);
        System.out.println("P3 " + p3);
    }
}
