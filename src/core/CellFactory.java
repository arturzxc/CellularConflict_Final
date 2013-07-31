/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import controllers.Main;
import controllers.Settings;
import core.physics.Physics;
import core.physics.Point2D;
import core.physics.Spatial;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates cells and ads them to physics object.
 *
 * @author Artur Krzynowek
 */
public class CellFactory {

    private int maxSize;
    private final Physics physics;

    public CellFactory(ObstacleFactory obs) {
        this.physics = Physics.getInstance();
        maxSize = Settings.getInstance().getMaxCells();
    }

    /**
     * triggered by main controller.
     */
    public void tick() {
        for (int i = 0; i < physics.size(); i++) {
            Spatial spatial = physics.get(i);
            Cell cell;
            if (spatial instanceof Cell) {
                cell = (Cell) spatial;
                cell.simpleUpdate();
            }
        }
    }

    public void generateSingleCell(int cType, int x, int y, int w, int h) {
        Random r = new Random();
        Cell c = null;
        switch (cType) {
            case 0:
                c = new BlueCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            case 1:
                c = new RedCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            case 2:
                c = new YellowCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            case 3:
                c = new GreenCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            default:
                break;
        }

        if (physics.isAllowed(c)) {
            c.setAge(200);//adult cell
            physics.add(c);
        } else {
            Main.log("Cannot add cell here. try another location");
        }
    }

    /*
     * This method generates a number of cells of specific type. The x and y is
     * the starting location of the area where the cells will be generated and w
     * and h are the dimensions of that area.
     */
    public ArrayList<Cell> generateCells(int n, int cellType, int x, int y, int w, int h) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            //create cell
            Cell c = createAtchetype(cellType, x, y, w, h);
            /*
             * checking for null as createArchetype is now always successfull
             * sometimes it return null e.g. there is not enough space for cells
             * to be created.
             */

            if (c != null) {
                c.setAge(200);//adult cell
                cells.add(c);//add to the list
                physics.add(c);//add to physics.
            }
        }
        return cells;
    }

    /*
     * This method create a cell in a specific place.
     */
    private Cell createAtchetype(int cType, int x, int y, int w, int h) {
        Random r = new Random();
        Cell c = null;

        switch (cType) {
            case 0:
                c = new BlueCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            case 1:
                c = new RedCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            case 2:
                c = new YellowCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            case 3:
                c = new GreenCell(
                        physics.nextIndex(),
                        null,
                        new Point2D(r.nextInt(Settings.getInstance().getGridWidth()), r.nextInt(Settings.getInstance().getGridHeight())),
                        Settings.getInstance().getCellWidth(),
                        Settings.getInstance().getCellHeight(),
                        1);
                break;
            default:
                break;
        }



        int attempt = 0;
        do {
            if (attempt == 10) {//prevents infinite loop when no space.
                return null;
            }
            float x2 = x + r.nextInt(w);
            float y2 = y + r.nextInt(h);
            c.setLocation(new Point2D(x2, y2));
            attempt++;
            //check if cell is allowed in that place using physics.
        } while (!physics.isAllowed(c));
        return c;
    }

    /**
     * Return number of cells by color.
     * @param color
     * @return 
     */
    public int getNumByCol(int color) {
        int counter = 0;
        for (int i = 0; i < physics.size(); i++) {
            Spatial spatial = physics.get(i);
            Cell cell;
            if (spatial instanceof Cell) {
                cell = (Cell) spatial;
                if (cell.getType() == color) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Cell getCellAtPos(int x, int y) {
        for (int i = 0; i < physics.size(); i++) {
            Spatial spatial = physics.get(i);
            Cell cell;
            if (spatial instanceof Cell) {
                cell = (Cell) spatial;
                if (cell.getLocation().x == x && cell.getLocation().y == y) {
                    return cell;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Cell factory";
    }
}
