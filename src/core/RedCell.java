package core;

import controllers.Settings;
import core.physics.PhyInfo;
import core.physics.Point2D;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Artur Krzynowek
 */
public class RedCell extends Cell {

    private PhyInfo victimInfo = new PhyInfo();
    private int attackRange = 15;
    private int damage = 5;

    public RedCell(int id, Cell parentCell, Point2D currentLocation, int width, int height, int generationCount) {
        super(id, parentCell, currentLocation, width, height, generationCount);
        initFields();
    }

    private void initFields() {
        linkColor = Color.red;
        currSpeed = 2.3f;
        detectionRange = 90;
        fieldOfView = 60;
        predatorOf = 0;
        prayOf = 2;
        currSpeed = 2.5f;

    }

    @Override
    public void update(PhyInfo phyInfo) {
        if (chase(phyInfo)) {
            kill();
        }
    }

    /**
     * Checks whether spatial is a valid pray and sets destination point to be
     * prays location.
     *
     * @param phyInfo
     * @return
     */
    private boolean chase(PhyInfo phyInfo) {
        if (phyInfo.spatial != null && phyInfo.spatial instanceof GreenCell) {
            victimInfo = phyInfo;
            setDestinationAndOriginalPoint(victimInfo.spatial.getLocation());
            return true;
        }
        return false;
    }

    /**
     * When close enough to the pray, this method will trigger takeDamage method
     * on the pray to kill it. Once health is 0 the pray will be dead.
     */
    private void kill() {
        if (victimInfo.spatial != null && victimInfo.distance < attackRange) {
            //we know its cell because chase makes sure of it
            Cell c = (Cell) victimInfo.spatial;
            c.takeDamage(damage);
        }
    }

    /**
     * reproduce in a free place around this cell.
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
            Cell child = new RedCell(
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
    public String typeToString() {
        return "Red";
    }

    /**
     * Draw this cell.
     *
     * @param g2d
     */
    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);

        //Draw Cell
        g2d.fillOval(
                (int) currLocation.x,
                (int) currLocation.y,
                w,
                h);

    }

    @Override
    protected int getType() {
        return 1;
    }
}
