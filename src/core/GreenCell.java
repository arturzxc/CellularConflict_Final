
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
public class GreenCell extends Cell {

    public GreenCell(int id, Cell parentCell, Point2D currentLocation, int width, int height, int generationCount) {
        super(id, parentCell, currentLocation, width, height, generationCount);
        currSpeed = 1.8f;
    }

    @Override
    public void update(PhyInfo phyInfo) {        
        float angle = Vector2D.calcAngleDegrees(currVelocity, phyInfo.spatial.getVelocity());
        if (!separate(phyInfo.spatial, phyInfo.distance)) {
            group(angle, phyInfo.spatial);
        }        

    }


    /*
     * This method groups cells by modyfying currVelocity which will be used
     * later to move this cell. Modyfication is simply combining this cell's
     * currVelocity vector with the currVelocity of the cell from the list and
     * then normalising it and multiplying by speed. Angle is used to determine
     * whether the cell from the list is in field of view. Only cells in field
     * of view are used for grouping and also of the same type.
     */
    private void group(float angle, Spatial spatial) {
        if (angle < fieldOfView && spatial instanceof GreenCell) {
            Cell cell = (Cell) spatial;
            if (cell instanceof GreenCell) {

                currVelocity.add(cell.getVelocity());
                currVelocity.normalise();
                currVelocity.multiply(currSpeed);
            }
        }
    }

    /*
     * If this cell is too close to a spatial then try calculate vector 
     * then calculate vector between them and revert it to opposite direction 
     * and use it as currVelocity vector to escape/separate.
     */
    private boolean separate(Spatial spatial, float distance) {
        if (spatial instanceof Cell) {
            Cell cell = (Cell) spatial;
            if (distance < separationRange || cell instanceof RedCell) {
                currVelocity = new Vector2D(0, 0);
                currVelocity =currLocation.vectorTo(spatial.getLocation());
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
            Cell child = new GreenCell(
                    physics.nextIndex(),
                    this,
                    childLoc,
                    Settings.getInstance().getCellWidth()/2,
                    Settings.getInstance().getCellHeight()/2,
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
    protected int getType() {
        return 3;
    }

    @Override
    public String typeToString() {
        return "Green";
    }

    @Override
    protected void draw(Graphics2D g2d) {
        boolean drawParentLink = false;
        g2d.setColor(Color.green);
        if (drawParentLink) {
            Cell c = parentCell;
            if (c != null) {
                g2d.drawLine(
                        (int) currLocation.x + w / 2,
                        (int) currLocation.y + h / 2,
                        (int) c.getLocation().x + c.getW() / 2,
                        (int) c.getLocation().y + c.getH() / 2);
            }
        }
        //Draw Cell
        g2d.fillOval(
                (int) currLocation.x,
                (int) currLocation.y,
                w,
                h);
    }
}
