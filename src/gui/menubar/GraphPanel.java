/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menubar;

import controllers.Main;
import controllers.Settings;
import core.Cell;
import core.CellFactory;
import core.ObstacleFactory;
import core.physics.Physics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Artur Krzynowek
 */
public class GraphPanel extends JPanel {

    private CellFactory cells;
    private ArrayList<Integer> numOfRed = new ArrayList<>();
    private ArrayList<Integer> numOfBlue = new ArrayList<>();
    private ArrayList<Integer> numOfGreen = new ArrayList<>();
    private ArrayList<Integer> numOfYellow = new ArrayList<>();
    private int graphLength = 350;

    public GraphPanel() {
        setBackground(Color.white);
        this.cells = Main.core.getCellList();        

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        //DRAW NUMBER OF Stats

        int width = getWidth();
        int height = getHeight();
        int border = 25;
        int paddingFromLeft = width - 200;
        g2d.setColor(new Color(44, 44, 44));
        g2d.drawString("Frames/Second: " + Main.controller.getFPS(), paddingFromLeft + border, 20 + border);
        g2d.drawString("Total cells: " + Physics.getInstance().size(), paddingFromLeft + border, 40 + border);

        g2d.drawString("Blue cells: " + cells.getNumByCol(Cell.BLUE), paddingFromLeft + border, 60 + border);
        g2d.drawString("Yellow cells: " + cells.getNumByCol(Cell.YELLOW), paddingFromLeft + border, 80 + border);
        g2d.drawString("Green cells: " + cells.getNumByCol(Cell.GREEN), paddingFromLeft + border, 100 + border);
        g2d.drawString("Red cells: " + cells.getNumByCol(Cell.RED), paddingFromLeft + border, 120 + border);

        g2d.drawString("Number of spatials (MAX: " + Settings.getInstance().getMaxCells() + ")", border + 10, border);
        g2d.drawString("Time[Ticks]", width - 80, height - (border / 2));

        g2d.drawLine(border, border, border, height - border); //Y AXIS
        g2d.drawLine(border, border, border - 5, border + 5);//left part of arrow
        g2d.drawLine(border, border, border + 5, border + 5);//right part of arrow

        g2d.drawLine(border, height - border, width - border, height - border);//X AXIS
        g2d.drawLine(width - border, height - border, width - border - 5, height - border + 5);//bottom part of arrow
        g2d.drawLine(width - border, height - border, width - border - 5, height - border - 5);//top part of arrow

        drawCellGraphs(g2d, border);

    }

    private void drawCellGraphs(Graphics2D g2d, int border) {
        //RED
        if (numOfRed.size() < graphLength) {
            numOfRed.add(0, cells.getNumByCol(Cell.RED));
        } else {
            numOfRed.remove(graphLength - 1);
            numOfRed.add(0, cells.getNumByCol(Cell.RED));
        }

        g2d.setColor(Color.red);
        for (int i = 0; i < numOfRed.size(); i++) {
            int count = numOfRed.get(i);
            if (count != 0) {
                g2d.drawRect(i + border,
                        scaleNumToHeight(count, border),
                        1,
                        1);
            }
        }
        //BLUE
        if (numOfBlue.size() < graphLength) {
            numOfBlue.add(0, cells.getNumByCol(Cell.BLUE));
        } else {
            numOfBlue.remove(graphLength - 1);
            numOfBlue.add(0, cells.getNumByCol(Cell.BLUE));
        }

        g2d.setColor(Color.blue);
        for (int i = 0; i < numOfBlue.size(); i++) {
            int count = numOfBlue.get(i);
            if (count != 0) {
                g2d.drawRect(i + border,
                        scaleNumToHeight(count, border),
                        1,
                        1);
            }
        }
        //GREEN
        if (numOfGreen.size() < graphLength) {
            numOfGreen.add(0, cells.getNumByCol(Cell.GREEN));
        } else {
            numOfGreen.remove(graphLength - 1);
            numOfGreen.add(0, cells.getNumByCol(Cell.GREEN));
        }

        g2d.setColor(Color.green);
        for (int i = 0; i < numOfGreen.size(); i++) {
            int count = numOfGreen.get(i);
            if (count != 0) {
                g2d.drawRect(i + border,
                        scaleNumToHeight(count, border),
                        1,
                        1);
            }
        }
        //YELLOW
        if (numOfYellow.size() < graphLength) {
            numOfYellow.add(0, cells.getNumByCol(Cell.YELLOW));
        } else {
            numOfYellow.remove(graphLength - 1);
            numOfYellow.add(0, cells.getNumByCol(Cell.YELLOW));
        }

        g2d.setColor(Color.yellow);
        for (int i = 0; i < numOfYellow.size(); i++) {
            int count = numOfYellow.get(i);
            if (count != 0) {
                g2d.drawRect(i + border,
                        scaleNumToHeight(count, border),
                        1,
                        1);
            }

        }
    }

    /*
     * Only repaints this dialog when it is visible.
     */
    public void render() {
        if (isVisible()) {
            repaint();
        }
    }

    private int scaleNumToHeight(int num, int border) {
        double h = getHeight() - border * 2;
        double ratio = h / Settings.getInstance().getMaxCells();
        double num2 = num * ratio;
        num2 = h - num2;
        return (int) num2 + border;
    }
}
