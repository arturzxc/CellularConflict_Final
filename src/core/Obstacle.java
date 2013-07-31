
package core;

import core.physics.Physics;
import core.physics.Point2D;
import core.physics.Spatial;
import core.physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import utils.ResourceLoader;

/**
 *
 * @author Artur Krzynowek
 */
public class Obstacle implements Spatial {

    public int w, h;
    public Point2D location;
    private int id;
    private boolean selected = false;

    public Obstacle(int id, int x, int y, int w, int h) {
        this.id = id;
        this.w = w;
        this.h = h;
        location = new Point2D(x, y);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Point2D getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point2D vector) {
        this.location = vector;
    }

    @Override
    public Vector2D getVelocity() {
        return new Vector2D(0, 0);
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public String getCollisionShape() {
        return "rectangle";

    }

    @Override
    public void setSpatialColided(Spatial s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Spatial getSpatialColided() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCollisionShape(String str) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getSeparationRange() {
        return 0;
    }

    @Override
    public void setSeparationRange(float separationRange) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getDetectionRange() {
        return 0;
    }

    @Override
    public void setDetectionRange(float detectionRange) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void simpleDraw(Graphics2D g2d) {

        g2d.drawImage(ResourceLoader.loadImageIcon("stone.jpg").getImage(),
                (int) location.x, (int) location.y, w, h, null);

        if (selected) {
            g2d.setColor(Color.green);
            g2d.drawRect((int) location.x, (int) location.y, w, h);
        }
    }

    @Override
    public boolean delete() {
        return Physics.getInstance().remove(this);
    }
}
