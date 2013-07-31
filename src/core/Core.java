/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import controllers.Settings;
import core.physics.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Artur Krzynowek
 */
public class Core {

    private ObstacleFactory obstacleList;
    private CellFactory cellList;
    private VirusFactory virusFactory;

    public Core() {

        obstacleList = new ObstacleFactory();
        cellList = new CellFactory(obstacleList);
        virusFactory = new VirusFactory();



         presetOfHeuristicMovement();//switch off reproduction for that        
        //presetOfFlocking();
        //presetChaseKill();

    }

    private void presetChaseKill() {
        obstacleList.generateObstacles(10);
        cellList.generateCells(450, Cell.GREEN, 300, 200, 700, 700);
        cellList.generateCells(10, Cell.RED, 150, 150, 10, 10);
    }

    private void presetOfFlocking() {
        obstacleList.generateObstacles(10);
        cellList.generateCells(500, Cell.GREEN, 300, 200, 700, 700);
    }

    private void presetOfHeuristicMovement() {
        for (int i = 0; i < 1000; i += 100) {
            obstacleList.generateSinglObstacle(300, i, 30, 30);
        }

        for (int i = 50; i < 1000; i += 100) {
            obstacleList.generateSinglObstacle(500, i, 30, 30);
        }

        for (int i = 80; i < 1000; i += 100) {
            obstacleList.generateSinglObstacle(400, i, 30, 30);
        }


        ArrayList<Cell> cells = cellList.generateCells(500, Cell.GREEN, 10, 300, 100, 500);
        for (Cell c : cells) {
            c.setDestinationAndOriginalPoint(new Point2D(1000, 500));
        }

    }

    /**
     * Models update method triggered by main controller.
     */
    public void update() {
        virusFactory.tick();
        cellList.tick();
    }

    public CellFactory getCellList() {
        return cellList;
    }

    public ObstacleFactory getObstacleList() {
        return obstacleList;
    }

    public VirusFactory getVirusFactory() {
        return virusFactory;
    }
}
