package core;

import core.physics.Point2D;

/**
 * 
 * @author Artur Krzynowek
 */
public class Virus {

    private int color;
    private int type;
    private Point2D location;
    private int radius;
    private int timeLeft = 50;

    public int getColor() {
        return color;
    }

    public int getType() {
        return type;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getRadius() {
        return radius;
    }

    public Virus(int color, int type, Point2D location, int radius) {
        this.color = color;
        this.type = type;
        this.location = location;
        this.radius = radius;
    }

    public void tick() {
        //to complete.
    }
}