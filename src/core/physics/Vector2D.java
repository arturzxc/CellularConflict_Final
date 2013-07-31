package core.physics;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Artur Krzynowek
 */
public class Vector2D {

    public float x = 0.0f;
    public float y = 0.0f;

    /**
     * Default constructor not allowed.
     */
    private Vector2D() {
    }

    /**
     * Add vector
     */
    public void add(Vector2D vector) {
        this.x += vector.x;
        this.y += vector.y;
    }


    /**
     * Divide vector by a scalar
     */
    public void divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    /**
     * Multiply vector by a scalar
     */
    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Main Constructor
     */
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor that copies value of passed vector and creates a new one with
     * x and y of the passed one
     */
    public Vector2D(Vector2D vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    /**
     * Set values of the vector to the values of the passed vector
     */
    public void set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Set vector values to the passed values
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates length using a*a + y*y = c*c This is from Pythagoras theorem.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /*
     * Divide x and y by the length of the vector to normalise it.
     */
    public void normalise() {
        float length = length();
        if (length != 0.0) {//this will prevent division by 0
            x /= length;
            y /= length;
        } else {
            x = 0.0f;
            y = 0.0f;
        }
    }

    /**
     * Check if x and y are zeroes
     */
    public boolean isZero() {
        return (Float.compare(x, 0) == 0 && Float.compare(y, 0) == 0);
    }

    /**
     * return a fresh copy of this vector
     */
    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    /**
     * Calculates the dot product of two vectors. This helps to know whether the
     * two vector are pointing in the same or opposite directions.
     */
    public static float dotProduct(Vector2D v1, Vector2D v2) {
        return (v2.x * v1.x) + (v2.y * v1.y);
    }

    /**
     * Calculate angle between two vectors. Dot product is required along with
     * multiplied of lengths of the vectors. We divide dot product by the
     * multiplied length and then put it in Math.acos function which will give
     * us theta in radians. we convert it to degrees using another method This
     * is a standard mathematical formula.
     */
    public static float calcAngleDegrees(Vector2D v1, Vector2D v2) {
        float dotProduct = dotProduct(v2, v1);
        float multLenght = v2.length() * v1.length();
        return (float) Math.toDegrees(Math.acos(dotProduct / multLenght));
    }

    @Override
    public boolean equals(Object o) {
        Vector2D v = (Vector2D) o;
        return (Float.compare(x, v.x) == 0 && Float.compare(y, v.y) == 0);
    }

    /**
     * Turns the vector in the opposite direction
     */
    public Vector2D revert() {
        this.x = 0 - x;
        this.y = 0 - y;
        return this;
    }

    @Override
    public String toString() {
        DecimalFormat myFormatter = new DecimalFormat("00.00");
        return "x: " + myFormatter.format(x) + "\t y: " + myFormatter.format(y);
    }

    /**
     * randomize x and y of the vector between -1 and 1.
     */
    public void randomise() {
        Random gen = new Random();
        x = gen.nextFloat() * 2 - 1;
        y = gen.nextFloat() * 2 - 1;
    }

    /**
     * FOR TESTING PURPOSES
     */
    public static void main(String str[]) {
    }
}
