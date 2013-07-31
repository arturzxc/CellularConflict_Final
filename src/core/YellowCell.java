/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import controllers.Main;
import controllers.Settings;
import core.physics.PhyInfo;
import core.physics.Point2D;
import core.physics.Spatial;
import core.physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class YellowCell extends Cell {

    private PhyInfo victimInfo = new PhyInfo();
    private int attackRange = w * 2;
    private int damage = 1;

    public YellowCell(int id, Cell parentCell, Point2D currentLocation, int width, int height, int generationCount) {
        super(id, parentCell, currentLocation, width, height, generationCount);
        currSpeed = 1.8f;
        detectionRange = 100;
    }

    /**
     * Whenever a cell that is a valid pray is around, chase it and kill it
     * otherwise flock.
     * @param phyInfo 
     */
    @Override
    public void update(PhyInfo phyInfo) {

        if (chase(phyInfo, 60)) {
            kill();
        } else {
            float angle = Vector2D.calcAngleDegrees(currVelocity, phyInfo.spatial.getVelocity());
            if (!separate(phyInfo.spatial, phyInfo.distance, separationRange)) {
                group(angle, phyInfo.spatial);
            }
        }


    }

    /**
     * reproduce in a free spot near this cell.
     */
    @Override
    public void reproduce() {
        if (age <= 200) {//children don' treproduce
            return;
        }
        if (currentReproduceTime >= maxReproduceTime) {
            int startX = 0 - w + gen.nextInt(w * 2);
            int startY = 0 - h + gen.nextInt(h * 2);
            Point2D childLoc = new Point2D(currLocation.x + startX + 1, currLocation.y + startY + 1);
            Cell child = new YellowCell(
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
    
    /**
     * Checks whether spatial is a valid pray and sets destination point to be
     * around prays location at a certain distance so other cells
     * of the same type  can help to surround it.
     */
    private boolean chase(PhyInfo phyInfo, int distanceToVictim) {
        if (phyInfo.spatial != null && phyInfo.spatial instanceof RedCell) {
            victimInfo = phyInfo;
            if (phyInfo.distance > distanceToVictim) {
                setDestinationAndOriginalPoint(null);
                separate(phyInfo.spatial, phyInfo.distance, distanceToVictim);
                return true;
            } else {
                Point2D surroundingPoint = victimInfo.spatial.getLocation().copy();
                surroundingPoint.x += -distanceToVictim + gen.nextInt((int) distanceToVictim * 2);
                surroundingPoint.y += -distanceToVictim + gen.nextInt((int) distanceToVictim * 2);
                setDestinationAndOriginalPoint(surroundingPoint);
                return true;
            }
        }
        return false;
    }

    /**
     * Deal damage to the pray until it dies or gets away.
     */
    private void kill() {
        if (victimInfo.spatial != null && victimInfo.distance < attackRange) {
            //we know its cell because chase makes sure of it
            Cell c = (Cell) victimInfo.spatial;
            c.takeDamage(damage);
        }
    }

    /**
     * This method groups cells by modifying currVelocity which will be used
     * later to move this cell. modification is simply combining this cells
     * currVelocity vector with the currVelocity of the cell from the list and
     * then normalizing it and multiplying by speed. Angle is used to determine
     * whether the cell from the list is in field of view. Only cells in field
     * of view are used for grouping and also of the same type.
     */
    private void group(float angle, Spatial spatial) {
        if (angle < fieldOfView && spatial instanceof YellowCell) {
            Cell cell = (Cell) spatial;
            if (cell instanceof YellowCell) {
                currVelocity.add(cell.getVelocity());
                currVelocity.normalise();
                currVelocity.multiply(currSpeed);
            }
        }
    }

    /**
     * If this cell is too close to a spatial then try calculate vector then
     * calculate vector between them and revert it to opposite direction and use
     * it as currVelocity vector to escape/separate.
     */
    private boolean separate(Spatial spatial, float distance, float sepDist) {
        if (spatial instanceof Cell) {
            if (distance < sepDist) {
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
    public String typeToString() {
        return "Yellow";
    }

    @Override
    protected int getType() {
        return 2;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        //Draw Cell
        g2d.setColor(Color.yellow);
        g2d.fillOval(
                (int) currLocation.x,
                (int) currLocation.y,
                w,
                h);
    }
}
