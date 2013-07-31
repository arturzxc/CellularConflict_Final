/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Artur Krzynowek
 * This simply stores the settings which the program uses
 * to setup itself
 */
public class Settings {

    private boolean running = true;
    private boolean paused = true;
    private int wHeight = 650;
    private int wWidth = 1000;
    private int gridWidth = 625;
    private int gridHeight = 472;
    private int padding = 15;
    private int cellWidth = 10;
    private int cellHeight = 10;
    private int delay = 16;
    private int maxCells = 500;
    private int maxObstacles = 50;
    private static Settings theInstance = null;

    private Settings() {
    }
    

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    public void togglePaused(){
        this.paused = !paused;
    }

    public static Settings getInstance() {
        if (theInstance == null) {
            theInstance = new Settings();
        }
        return theInstance;
    }

    public boolean isRunning() {
        return running;
    }

    public void setIsRunning(boolean isRunning) {
        this.running = isRunning;
    }

    public int getwHeight() {
        return wHeight;
    }

    public void setwHeight(int wHeight) {
        this.wHeight = wHeight;
    }

    public int getwWidth() {
        return wWidth;
    }

    public void setwWidth(int wWidth) {
        this.wWidth = wWidth;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getMaxCells() {
        return maxCells;
    }

    public void setMaxCells(int maxCells) {
        this.maxCells = maxCells;
    }

    public int getMaxObstacles() {
        return maxObstacles;
    }

    public void setMaxObstacles(int maxObstacles) {
        this.maxObstacles = maxObstacles;
    }

    public Settings getTheInstance() {
        return theInstance;
    }

}
