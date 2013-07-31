package core.physics;

import controllers.Settings;
import java.util.*;

/**
 * This is a singleton. It holds the list of all the physics objects i.e.
 * spatial object. It is responsible for collision checking and returning
 * information about surrounding world when an agent requests it.
 *
 * @author Artur Krzynowek
 */
public class Physics implements List<Spatial> {

    private static Physics theInstance;
    private List<Spatial> spatialList = new ArrayList<>(); //Must be of type list        
    private int indexer = 0;

    private Physics() {
    }//No Instaqnces here.

    public int nextIndex() {
        return indexer++;
    }

    public static Physics getInstance() {
        if (theInstance == null) {
            theInstance = new Physics();
            return theInstance;
        } else {
            return theInstance;
        }
    }

    /**
     * First check if an object has a shape of square or circle.
     * once done, apply appropriate algorithm to check for collisions.
     * @param spatial
     * @return 
     */
    private Spatial isColliding(Spatial spatial) {
        ArrayList<PhyInfo> spatialsInRange = getAllSpatialsWithinRange(spatial);
        if (spatialsInRange == null || spatialsInRange.isEmpty()) {
            return null;
        }
        for (int i = 0; i < spatialsInRange.size(); i++) {
            Spatial oppositeSpatial = spatialsInRange.get(i).spatial;
            float distance = spatialsInRange.get(i).distance;

            if (oppositeSpatial.getCollisionShape().equals("circle")) {
                if (spatial.getW() > oppositeSpatial.getW()) {
                    if (distance < spatial.getW() + 2) {
                        spatial.setSpatialColided(oppositeSpatial);
                        return oppositeSpatial;
                    }
                } else {
                    if (distance < oppositeSpatial.getW() + 2) {
                        spatial.setSpatialColided(oppositeSpatial);
                        return oppositeSpatial;
                    }
                }

            } else {
                boolean xIntersect = rangesIntersect(
                        spatial.getLocation().x,
                        spatial.getLocation().x + spatial.getW(),
                        oppositeSpatial.getLocation().x,
                        oppositeSpatial.getLocation().x + oppositeSpatial.getW());

                boolean yIntersect = rangesIntersect(
                        spatial.getLocation().y,
                        spatial.getLocation().y + spatial.getH(),
                        oppositeSpatial.getLocation().y,
                        oppositeSpatial.getLocation().y + oppositeSpatial.getH());

                if (xIntersect && yIntersect) {
                    spatial.setSpatialColided(oppositeSpatial);
                    return oppositeSpatial;
                }
            }

        }
        return null;
    }

    /**
     * Check if range 1 is contained in range 2. Could be useful for 
     * calculating intersecting lines and therfore ray casting.
     * @param min1
     * @param max1
     * @param min2
     * @param max2
     * @return 
     */
    public static boolean rangesIntersect(float min1, float max1, float min2, float max2) {
        boolean test1 = false;
        boolean test2 = false;
        if ((min1 >= min2) && (min1 <= max2)) {
            test1 = true;
        }
        if ((max1 >= min2) && (max1 <= max2)) {
            test2 = true;
        }
        return test1 || test2;
    }

    /**
     * Check if the spatial is colliding with something and if it is
     * in bounds of the grid.
     * @param spatial
     * @return 
     */
    public boolean isAllowed(Spatial spatial) {
        if (!isInBounds(spatial) || isColliding(spatial) != null) {
            return false;
        }
        return true;
    }

    /**
     * Based on the detection range, a list of PhyInfo objects is
     * returned that contains spatial objects and distances to the 
     * agent that called this method.
     * @param client
     * @return 
     */
    public ArrayList<PhyInfo> getAllSpatialsWithinRange(Spatial client) {
        ArrayList<PhyInfo> inRange = new ArrayList<>();
        for (int i = 0; i < spatialList.size(); i++) {
            Spatial spatial = spatialList.get(i);
            if (spatial != client) {//omit the spatial that calls this method. 
                float detectRange = client.getDetectionRange();
                float distance = Point2D.distance(spatial.getLocation(), client.getLocation());
                if (distance < detectRange) {
                    PhyInfo info = new PhyInfo(spatial, distance);
                    inRange.add(info);
                }
            }
        }
        return inRange;
    }

    /**
     * Check if spatial is in bounds of the grid.
     * @param s
     * @return 
     */
    private boolean isInBounds(Spatial s) {
        Point2D loc = s.getLocation();
        Settings sett = Settings.getInstance();
        if (loc.x <= sett.getPadding()
                || loc.x + s.getW() + sett.getPadding() > sett.getGridWidth()) {
            return false;
        }
        if (loc.y <= sett.getPadding()
                || loc.y + s.getH() + sett.getPadding() > sett.getGridHeight()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "-=====  Physics v1.0  =====-";
    }

    @Override
    public int size() {
        return spatialList.size();
    }

    @Override
    public boolean isEmpty() {
        return spatialList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return spatialList.contains(o);
    }

    @Override
    public Iterator<Spatial> iterator() {
        return spatialList.iterator();
    }

    @Override
    public Object[] toArray() {
        return spatialList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return spatialList.toArray(a);
    }

    @Override
    public boolean add(Spatial e) {
        if (spatialList.size() < Settings.getInstance().getMaxCells()) {
            return spatialList.add(e);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return spatialList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return spatialList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Spatial> c) {
        return spatialList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Spatial> c) {
        return spatialList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return spatialList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return spatialList.retainAll(c);
    }

    @Override
    public void clear() {
        spatialList.clear();
    }

    @Override
    public Spatial get(int index) {
        return spatialList.get(index);
    }

    @Override
    public Spatial set(int index, Spatial element) {
        return spatialList.set(index, element);
    }

    @Override
    public void add(int index, Spatial element) {
        spatialList.add(index, element);
    }

    @Override
    public Spatial remove(int index) {
        return spatialList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return spatialList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return spatialList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Spatial> listIterator() {
        return spatialList.listIterator();
    }

    @Override
    public ListIterator<Spatial> listIterator(int index) {
        return spatialList.listIterator(index);
    }

    @Override
    public List<Spatial> subList(int fromIndex, int toIndex) {
        return spatialList.subList(fromIndex, toIndex);
    }

   /**
    * Test main.
    * @param args 
    */
    public static void main(String args[]) {
        int min1 = 200;
        int max1 = 600;
        int min2 = 5;
        int max2 = 300;
        System.out.println("INTERSECT: " + rangesIntersect(min1, max1, min2, max2));
    }
}
