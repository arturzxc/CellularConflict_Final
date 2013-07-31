
package core.physics;

import java.awt.Graphics2D;

/**
 * Interface that every agent must implement
 * in order to be added to physics.
 * @author Artur Krzynowek
 */
public interface Spatial {    
    public Point2D getLocation();
    public void setLocation(Point2D vector);        
    public Vector2D getVelocity();
    public int getW();
    public int getH();
    public float getSeparationRange();
    public void setSeparationRange(float separationRange);
    public float getDetectionRange();
    public void setDetectionRange(float detectionRange);
    public String getCollisionShape();
    public void setSpatialColided(Spatial s);
    public Spatial getSpatialColided();
    public void setCollisionShape(String str);   
    public int getId();    
    public boolean isSelected();    
    public void setSelected(boolean selected);
    public boolean delete();
    public void simpleDraw(Graphics2D g2d);
}
