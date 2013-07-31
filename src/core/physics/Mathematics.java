/*
 * Static methods   
 */
package core.physics;

/**
 *
 * @author Artur Krzynowek
 */
public class Mathematics {

    private Mathematics() {
    }

    /*
     * Given origin location, degreed and radius this method
     * will calculate point on a circle. 
     * The parametric equation for a circle is
     * x = cx + r * cos(a)
     * y = cy + r * sin(a)
     * 
     */
    public static Point2D pointOnCircle(Point2D origin, float degrees, float radius) {
        Point2D p = new Point2D(0, 0);
        p.x = origin.x + radius * (float) Math.cos(Math.toRadians(degrees));
        p.y = origin.y + radius * (float) Math.sin(Math.toRadians(degrees));
        return p;
    }
}
