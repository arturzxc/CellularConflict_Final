package core;

import controllers.Main;
import controllers.Settings;
import core.physics.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Map.Entry;
import java.util.*;
import javax.swing.ImageIcon;
import utils.ResourceLoader;

/**
 *
 * @author Artur Krzynowek
 */
public abstract class Cell implements Spatial {

    public static final int BLUE = 0;
    public static final int RED = 1;
    public static final int YELLOW = 2;
    public static final int GREEN = 3;
    protected ImageIcon imgIcon;
    protected int health;
    protected int w, h;
    protected Random gen;
    protected ArrayList<Cell> childreen;
    protected Cell parentCell;
    protected int currentReproduceTime;
    protected int maxReproduceTime;
    protected int maxChildreen;
    protected int maxLinkLength;
    protected Color linkColor;
    protected int number;
    protected int direction;
    protected Vector2D lastPos;
    protected int generationCount;
    protected Physics physics;
    protected Spatial colidedSpatial;
    protected String collisionShape;
    protected Point2D currLocation;
    protected Point2D destinationPoint = null;
    //MOVE3 VARS
    protected int fieldOfView = 360;
    protected int maxMoves = 100;
    protected int movesLeft = 10;
    protected float theta = 0;// curent angle. 3 o'clock as origin 0 degrees and clockwise    
    protected int maxTurn = 45; //degrees of max turn    
    protected float maxSpeed = 3.0f;
    protected float currSpeed = 2.0f;
    protected float separationRange = 20;
    protected float detectionRange = 50;
    protected Vector2D currVelocity = new Vector2D(0, 0);
    protected boolean frozen = false;
    protected boolean selected = false;
    protected int id;
    protected int prayOf;
    protected int predatorOf;
    protected int hunger = 2000;
    protected int age;
    protected AttributeList attributeList = new AttributeList();
    protected ArrayList<Point2D> locationHistory = new ArrayList<>();
    protected ArrayList<PhyInfo> spatialsInRange = new ArrayList<>();
    protected Vector2D excludedVelocity = new Vector2D(0, 0);
    protected ArrayList<Point2D> cornersVisited = new ArrayList<>();
    protected Point2D originalDestinationPoint;
    protected boolean prevMoveSucceeded;
    protected Point2D savedDestinationPoint;

    /**
     * The point that this method sets will be a be a designation point for a
     * cell.
     *
     * @param loc
     */
    public void setDestinationAndOriginalPoint(Point2D loc) {
        this.originalDestinationPoint = loc;
        this.destinationPoint = loc;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public float getCurrentSpeed() {
        return currSpeed;
    }

    public int getHunger() {
        return hunger;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxTurn() {
        return maxTurn;
    }

    public int getHealth() {
        return health;
    }

    public float getTheta() {
        return theta;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean isFrozen) {
        this.frozen = isFrozen;
    }

    @Override
    public int getId() {
        return id;
    }

    public Cell() {
    }

    public Point2D getDestinationOriginalPoint() {
        return originalDestinationPoint;
    }

    public int getFieldOfView() {
        return fieldOfView;
    }

    @Override
    public Vector2D getVelocity() {
        return currVelocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.currVelocity = velocity;
    }

    public void setLinkColor(Color linkColor) {
        this.linkColor = linkColor;
    }

    public void setPrayOf(int prayOf) {
        this.prayOf = prayOf;
    }

    public void setPredatorOf(int predatorOf) {
        this.predatorOf = predatorOf;
    }

    public Vector2D getLastPos() {
        if (lastPos == null) {
            return new Vector2D(currLocation.x, currLocation.y);
        }
        return lastPos;
    }

    @Override
    public float getSeparationRange() {
        return separationRange;
    }

    @Override
    public void setSeparationRange(float separationRange) {
        this.separationRange = separationRange;
    }

    public ImageIcon getImgIcon() {
        return imgIcon;
    }

    @Override
    public float getDetectionRange() {
        return detectionRange;
    }

    @Override
    public void setDetectionRange(float detectionRange) {
        this.detectionRange = detectionRange;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public int getW() {
        return w;
    }

    public Cell getParentCell() {
        return parentCell;
    }

    public Color getLinkColor() {
        return linkColor;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Cell> getChildreen() {
        return childreen;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    @Override
    public void setLocation(Point2D point) {
        this.currLocation = point;
    }

    @Override
    public Point2D getLocation() {
        return currLocation;
    }

    @Override
    public void setSpatialColided(Spatial s) {
        colidedSpatial = s;
    }

    @Override
    public Spatial getSpatialColided() {
        return colidedSpatial;
    }

    @Override
    public String getCollisionShape() {
        return collisionShape;
    }

    @Override
    public void setCollisionShape(String str) {
        this.collisionShape = str;
    }

    /**
     * Main and only constructor
     *
     * @param id
     * @param parentCell
     * @param currentLocation
     * @param width
     * @param height
     * @param generationCount
     */
    public Cell(int id,
            Cell parentCell,
            Point2D currentLocation,
            int width, int height,
            int generationCount) {
        this.id = id;
        this.currLocation = currentLocation;
        this.frozen = false;
        this.physics = Physics.getInstance();
        this.childreen = new ArrayList<>();
        this.generationCount = generationCount;
        this.health = 100;
        this.parentCell = parentCell;
        this.gen = new Random();
        this.collisionShape = "circle";
        this.w = width;
        this.h = height;
        this.maxReproduceTime = gen.nextInt(200) + physics.size() * 3;//THE MORE CELLS THE SLOWER THEY REPRODUCE
        this.currentReproduceTime = 0;
        this.maxChildreen = 5;
        this.maxLinkLength = 100 + 10 * generationCount;
        this.number = physics.size();
        this.currVelocity = new Vector2D(0, 0);
        this.destinationPoint = new Point2D(gen.nextInt(Settings.getInstance().getwWidth() - 50), gen.nextInt(Settings.getInstance().getwHeight() - 50));
        this.age = 0;
        this.theta = gen.nextInt(360);

        randomiseVelocity(currVelocity);

    }

    public abstract String typeToString();

    /**
     * Taking damage decreases cells health and when health is less than 0 the
     * cell will be deleted.
     *
     * @param damage
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            delete();
        }
    }

    /**
     * History of locations is used by method unstuck() to determine whether a
     * cell is stuck.
     *
     * @param recordNo
     */
    public void saveLocationForHistory(int recordNo) {
        if (locationHistory.size() > recordNo) {
            locationHistory.remove(0);
        }
        locationHistory.add(currLocation.copy());
    }

    /**
     * Based on number of recorder locations that the cell visited, calculate
     * average distance between them and if it is below the threshold then
     * unstuck the cell by nullifying any points it is trying to reach.
     *
     * @param stuckThreshold
     * @param maxRecords
     */
    public void unstuck(float stuckThreshold, int maxRecords) {

        if (locationHistory.size() >= maxRecords - 1) {
            float totalDistance = 0f;
            for (int i = 1; i < locationHistory.size(); i++) {
                totalDistance += Point2D.distance(locationHistory.get(i - 1), locationHistory.get(i));
            }

            float avgDistance = totalDistance / locationHistory.size();
            Main.log("avgDistance" + avgDistance);
            if (avgDistance < stuckThreshold) {//Cell got stuck   
                originalDestinationPoint = null;
                randomiseVelocity(currVelocity);
                movesLeft = maxMoves;
                locationHistory.clear();
                colidedSpatial = null;
            }
        }
        saveLocationForHistory(maxRecords);
    }

    /**
     * Retreive necessary information so that a cell can make decisions
     */
    public void harvestInformation() {
        spatialsInRange = physics.getAllSpatialsWithinRange(this);
    }

    /**
     * Try to move towards the point of original destination. if unsuccessful
     * then Retreive information about spatial the cell collided with, calculate
     * its closes corner and set it to be new destination point. if destination
     * point is reached then compare it with original point of destination to
     * see whether they match. The corners are stored in a list so the cell
     * doesn't try to reach them more than twice unless all the corner were
     * already tried.
     *
     * @param reachThreshold
     * @return
     */
    protected boolean moveByPoint(int reachThreshold) {
        if (originalDestinationPoint != null) {

            if (destinationPoint == null) {
                destinationPoint = originalDestinationPoint.copy();
            }

            boolean succ = attemptMoveToPoint(destinationPoint, currLocation, currSpeed);
            boolean reached = destinationPoiontReached(destinationPoint, currLocation, 1);
            boolean destIsOrigin = destinationIsOrigin(originalDestinationPoint, destinationPoint);

            if (succ && reached && destIsOrigin) {//We reached Original destination point.
                originalDestinationPoint = null;
                destinationPoint = null;
                cornersVisited.clear();
            } else if (succ && reached && !destIsOrigin) {
                destinationPoint = originalDestinationPoint.copy();

            } else if (!succ) {

                destinationPoint = calcClosestCornerOfCollidedSpatial(cornersVisited, reachThreshold);

                if (destinationPoint != null) {
                    cornersVisited.add(destinationPoint.copy());
                } else {

                    cornersVisited.clear();
                    colidedSpatial = null;
                    currVelocity.revert();
                }

            }

            return true;
        }
        return false;
    }

    /**
     * Attempt to move using velocity
     */
    protected void moveByVelocity() {
        if (movesLeft > 0) {
            if (attemptMoveByVelocity(currVelocity, currLocation, currSpeed)) {
                movesLeft--;
            } else {
                currVelocity.revert();
            }
        } else {
            randomiseVelocity(currVelocity);
            movesLeft = maxMoves;
        }
    }

    public abstract void update(PhyInfo phyInfo);

    /**
     * This method is fetched by CellFactory. It trigger update() method which
     * all the subclasses must implement. It also does some simple things such
     * as harvesting information, growth and aging of the cell or health
     * regeneration.
     */
    public void simpleUpdate() {

        if (age > 200) {
            growUp();
            harvestInformation();
            for (int i = 0; i < spatialsInRange.size(); i++) {
                PhyInfo phyInfo = spatialsInRange.get(i);
                update(phyInfo);//GIVE INFO TO SUBCLASSES        

            }
            //the more cells the lower chance to reproduce
            if (gen.nextFloat() < 0.3) {/// physics.size() * 10) {
                //  reproduce();
            }
        }


        move(w * 2);
        age++;
        if (health < 100) {
            health++;
        }
    }

    /**
     * When a cell reaches certain age it grows. This method checks its new size
     * against physics and if it will not collide with something.
     */
    protected void growUp() {
        this.w = Settings.getInstance().getCellWidth();
        this.h = Settings.getInstance().getCellHeight();
        if (!physics.isAllowed(this)) {
            this.w = Settings.getInstance().getCellWidth() / 2;
            this.h = Settings.getInstance().getCellHeight() / 2;
        }
    }

    /**
     * If a point of destination is specified then move towards it using
     * heuristic function. Otherwise move using velocity.
     *
     * @param reachThreshold
     */
    protected void move(int reachThreshold) {
        if (!moveByPoint(reachThreshold)) {//heuristic move that avoids other spatials
            moveByVelocity();//random move or flocking
        }
        //unstuck(2, 500);
    }

    /**
     * Compares two points using their equals method.
     *
     * @param origin
     * @param dest
     * @return
     */
    protected boolean destinationIsOrigin(Point2D origin, Point2D dest) {
        if (origin.equals(dest)) {
            return true;
        }
        return false;
    }

    /**
     * Check whether cell is close enough to the destination point for it to
     * count as reached.
     *
     * @param dest
     * @param loc
     * @param threshold
     * @return
     */
    protected boolean destinationPoiontReached(Point2D dest, Point2D loc, float threshold) {
        if (Point2D.distance(dest, loc) < w + threshold) {
            return true;
        }
        return false;
    }

    /**
     * Calculate all the corners of the spatial the cell collided with and see
     * which one is closest and if that corner is not on the usedCorners list
     * then return it. Otherwise return next closes corner.
     *
     * @param alreadyUsedCorners
     * @param threshold
     * @return
     */
    public Point2D calcClosestCornerOfCollidedSpatial(ArrayList<Point2D> alreadyUsedCorners, int threshold) {

        Spatial s = getSpatialColided();
        if (s == null) {
            return null;
        }

        Point2D sLocation = s.getLocation();
        int sW = s.getW();
        int sH = s.getH();

        int dblW = threshold;

        Point2D topLeft = new Point2D(sLocation.x - dblW, sLocation.y - dblW);
        Point2D topRight = new Point2D(sLocation.x + sW + dblW, sLocation.y - dblW);
        Point2D bottomLeft = new Point2D(sLocation.x - dblW, sLocation.y + sH + dblW);
        Point2D bottomRight = new Point2D(sLocation.x + sW + dblW, sLocation.y + sH + dblW);

        float topLeftDistance = Point2D.distance(topLeft, currLocation);
        float topRightDistance = Point2D.distance(topRight, currLocation);
        float bottomLeftDistance = Point2D.distance(bottomLeft, currLocation);
        float bottomRightDistance = Point2D.distance(bottomRight, currLocation);

        Map<Float, Point2D> corners = new TreeMap<>();
        corners.put(topLeftDistance, topLeft);
        corners.put(topRightDistance, topRight);
        corners.put(bottomLeftDistance, bottomLeft);
        corners.put(bottomRightDistance, bottomRight);


        Iterator it = corners.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            Point2D point = (Point2D) entry.getValue();
            if (!alreadyUsedCorners.contains(point)) {
                return point;
            }
        }
        return null;
    }

    /**
     * Randomizes point to be within the simulations grid.
     *
     * @param point
     */
    public void randomisePoint(Point2D point) {
        if (point == null) {
            point = new Point2D(0, 0);
        }
        //point.randomise();
        point.x += gen.nextInt(Settings.getInstance().getGridWidth() - 1);
        point.y += gen.nextInt(Settings.getInstance().getGridHeight() - 1);
    }

    /**
     * Randomize, normalize and adjust to the speed
     */
    private void randomiseVelocity(Vector2D vel) {
        if (vel == null) {
            vel = new Vector2D(0, 0);
        }
        vel.randomise();
        vel.normalise();
        vel.multiply(currSpeed);
    }

    /**
     * Attempt to move according the the given currVelocity If successful then
     * the cell is moved otherwise revert move. This is a convenience method.
     */
    protected boolean attemptMoveByVelocity(Vector2D vel, Point2D currentLoc, float speed) {
        vel.normalise();
        vel.multiply(speed);
        currentLoc.addVelocity(vel);
        if (physics.isAllowed(this)) {
            return true;
        }
        currentLoc.subtractVelocity(vel);
        return false;

    }

    /*
     * Attempt to move according the the given currVelocity If succesfull then
     * the cell is moved otherwise revert move. This is a convenience method.
     */
    protected boolean attemptMoveToPoint(Point2D targetPoint, Point2D currentLoc, float speed) {
        if (targetPoint == null) {
            return true;
        }
        Vector2D vel = currentLoc.vectorTo(targetPoint);
        vel.normalise();
        vel.multiply(speed);
        currentLoc.addVelocity(vel);
        if (physics.isAllowed(this)) {
            return true;
        }
        currentLoc.subtractVelocity(vel);//revert move.
        return false;
    }

    @Override
    public boolean delete() {
        if (parentCell != null) {
            parentCell.getChildreen().remove(this);
        }

        for (Cell c : childreen) {
            c.removeParent();
        }

        return physics.remove(this);
    }

    public void removeParent() {
        this.parentCell = null;
    }

    public abstract void reproduce();

    protected abstract void draw(Graphics2D g2d);

    protected abstract int getType();

    /**
     * Draws various elements of a cell such as damage based on cells health.
     * Also triggers draw() implemented by subclasses.
     *
     * @param g2d
     */
    @Override
    public void simpleDraw(Graphics2D g2d) {
        //DRAWING FLAGS
        boolean drawRings = false;
        boolean drawDestinationLine = false;
        boolean drawViewCone = false;


        if (parentCell == null) {
            g2d.setColor(Color.pink);
            g2d.setBackground(Color.pink);
        }

        float w2 = w / 2;
        float h2 = h / 2;
        float radius = w;

        /*
         * Draw green circle around selected cells.
         */
        if (isSelected()) {


            g2d.setColor(new Color(40, 150, 40, 100));
            g2d.fillOval(
                    (int) (currLocation.x - radius + w2),
                    (int) (currLocation.y - radius + h2),
                    (int) (radius * 2),
                    (int) (radius * 2));
        }

        //draw rings around cells. They represent 
        //detection and separation range

        if (drawRings) {//DRAW RINGS?!?!    
            g2d.setColor(new Color(200, 200, 200, 50));
            radius = detectionRange;


            g2d.drawOval(
                    (int) (currLocation.x - radius + w2),
                    (int) (currLocation.y - radius + h2),
                    (int) (radius * 2),
                    (int) (radius * 2));


            radius = separationRange;

            g2d.drawOval(
                    (int) (currLocation.x - radius + w2),
                    (int) (currLocation.y - radius + h2),
                    (int) (radius * 2),
                    (int) (radius * 2));

            g2d.setColor(Color.red);
            g2d.drawString(id + "", (int) currLocation.x, (int) currLocation.y);
        }




        if (drawDestinationLine) {
            g2d.setColor(new Color(200, 200, 200, 50));
            float shift = w / 2;
            g2d.drawLine(
                    (int) (currLocation.x + shift),
                    (int) (currLocation.y + shift),
                    (int) (getDestinationOriginalPoint().x + shift),
                    (int) (getDestinationOriginalPoint().y + shift));
        }


        if (selected) {
            //DRAW DESTINATION POINT        
            g2d.setColor(Color.CYAN);
            if (destinationPoint != null) {
                g2d.fillOval((int) destinationPoint.x - 4, (int) destinationPoint.y - 4, 8, 8);
            }
            g2d.setColor(Color.pink);
            if (originalDestinationPoint != null) {
                g2d.fillOval((int) originalDestinationPoint.x - 4, (int) originalDestinationPoint.y - 4, 8, 8);
            }
        }

        //Draw health based damage on the cell(something like blood to indicate its state)       
        if (health < 80) {
            Image bloodStain = ResourceLoader.loadImageIcon("blood3.png").getImage();
            int healthDeficit = 100 - health;
            int scalar = 5;
            g2d.drawImage(bloodStain,
                    (int) currLocation.x - healthDeficit / 2 / scalar,
                    (int) currLocation.y - healthDeficit / 2 / scalar,
                    w + healthDeficit / scalar,
                    h + healthDeficit / scalar,
                    null);

        }


        //subclasses draw themselves
        draw(g2d);

        //Draw reflection on the cell
        g2d.setColor(new Color(255, 255, 255, 200));
        g2d.fillOval(
                (int) currLocation.x + 2,
                (int) currLocation.y + 2,
                w / 3,
                h / 3);

        if (age >= 800) {
            g2d.setColor(new Color(44, 44, 44, 80));
            g2d.fillOval(
                    (int) currLocation.x,
                    (int) currLocation.y,
                    w,
                    h);

        }
    }
}//END CELL

