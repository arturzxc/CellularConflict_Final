
package core;

import controllers.Settings;
import core.physics.PhyInfo;
import core.physics.Point2D;
import core.physics.Spatial;
import core.physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Artur Krzynowek
 */
public class BlueCell extends Cell {

    public BlueCell(int id, Cell parentCell, Point2D currentLocation, int width, int height, int generationCount) {
        super(id, parentCell, currentLocation, width, height, generationCount);
    }

    /**
     * This cell separates if other spatial objects are to close otherwise it 
     * groups with the cells of the same type. This behavior is flocking.
     */
    @Override
    public void update(PhyInfo phyInfo) {
        float angle = Vector2D.calcAngleDegrees(currVelocity, phyInfo.spatial.getVelocity());
        if (!separate(phyInfo.spatial, phyInfo.distance)) {
            group(angle, phyInfo.spatial);
        }
    }

    /**
     * This method groups cells by modifying currVelocity which will be used
     * later to move this cell. Modification is simply combining this cells
     * currVelocity vector with the currVelocity of the cell from the list and
     * then normalizing it and multiplying by speed. Angle is used to determine
     * whether the cell from the list is in field of view. Only cells in field
     * of view are used for grouping and also of the same type.
     */
    private void group(float angle, Spatial spatial) {
        if (angle < fieldOfView && spatial instanceof BlueCell) {
            Cell cell = (Cell) spatial;
            if (cell instanceof BlueCell) {
                currVelocity.add(cell.getVelocity());
                currVelocity.normalise();
                currVelocity.multiply(currSpeed);
            }
        }
    }

    /**
     * If this cell is too close to a spatial then try calculate vector 
     * then calculate vector between them and revert it to opposite direction 
     * and use it as currVelocity vector to escape/separate.
     */
    private boolean separate(Spatial spatial, float distance) {
        if (spatial instanceof Cell) {
            Cell cell = (Cell) spatial;
            if (distance < separationRange || cell instanceof GreenCell) {
                currVelocity = new Vector2D(0, 0);
                currVelocity = currLocation.vectorTo(spatial.getLocation());
                currVelocity.revert();
                currVelocity.normalise();
                currVelocity.multiply(currSpeed);

                return true;
            }
        }
        return false;
    }

    @Override
    public void reproduce() {
        if (age <= 200) {//children don' treproduce
            return;
        }
        if (currentReproduceTime >= maxReproduceTime) {
            int startX = 0 - w + gen.nextInt(w * 2);
            int startY = 0 - h + gen.nextInt(h * 2);
            Point2D childLoc = new Point2D(currLocation.x + startX + 1, currLocation.y + startY + 1);
            Cell child = new BlueCell(
                    physics.nextIndex(),
                    this,
                    childLoc,
                    Settings.getInstance().getCellWidth() / 2,
                    Settings.getInstance().getCellHeight() / 2,
                    generationCount++);

            if (physics.isAllowed(child)) {
                childreen.add(child);
                physics.add(child);
                currentReproduceTime = 0;
                maxReproduceTime += 100;
            }

        } else {
            currentReproduceTime++;
        }

    }

    @Override
    protected void draw(Graphics2D g2d) {
        //Draw Cell
        g2d.setColor(Color.blue);
        g2d.fillOval(
                (int) currLocation.x,
                (int) currLocation.y,
                w,
                h);
    }

    @Override
    protected int getType() {
        return 0;
    }

    @Override
    public String typeToString() {
        return "Blue";
    }
}
