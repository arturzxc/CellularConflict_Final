
package gui;

import controllers.Main;
import core.Cell;
import core.physics.Physics;
import core.physics.Point2D;
import core.physics.Spatial;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Artur Krzynowek
 */
public class SelectingPanel extends JPanel implements MouseMotionListener, MouseListener {

    private int x, y;
    private int w, h;
    private int saveX, saveY, saveW, saveH;
    private Color outline = new Color(50, 170, 50);
    private Color inside = new Color(50, 100, 50, 60);
    private Color outlinePers = new Color(50, 50, 170);
    private Color insidePers = new Color(50, 50, 100, 60);
    boolean persistantSelect = false;
    private List<Spatial> selectedSpatials = new ArrayList<>();

    public SelectingPanel() {

        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public List<Spatial> getSelectedSpatials() {
        return selectedSpatials;
    }

    public boolean isPersistantSelect() {
        return persistantSelect;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = y = w = h = 0;
        x = e.getX();
        y = e.getY();

        if (SwingUtilities.isRightMouseButton(e)) {


            int x2 = e.getX();
            int y2 = e.getY();
            Main.frame.displayMsg("Selected spatials: GoTo x:" + x2 + " y:" + y2);
            selectedSpatials = getSelectedSpatials();
            for (int i = 0; i < selectedSpatials.size(); i++) {
                Spatial spa = selectedSpatials.get(i);
                if (spa instanceof Cell) {
                    Cell cell = (Cell) spa;
                    cell.setDestinationAndOriginalPoint(new Point2D(x2, y2));

                }

            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        saveX = x;
        saveY = y;
        saveW = w;
        saveH = h;
        if (!persistantSelect) {
            x = y = w = h = 0;
        }
        if (!SwingUtilities.isRightMouseButton(e)) {//select only if it is not right mouse button
            selectSpatialsInRect(saveX, saveY, saveW, saveH);
        }
        Main.frame.getMyMenuBar().getInspectSpatialDialog().render(false, false);
    }

    public void selectSpatialsInRect(int x, int y, int w, int h) {
        selectedSpatials.clear();
        Physics p = Physics.getInstance();
        for (int i = 0; i < p.size(); i++) {
            Spatial spatial = p.get(i);
            if (spatial.getLocation().x > x
                    && spatial.getLocation().x < x + w
                    && spatial.getLocation().y > y
                    && spatial.getLocation().y < y + h) {
                spatial.setSelected(true);
                selectedSpatials.add(spatial);

            } else {

                spatial.setSelected(false);

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (w <= 0 || h <= 0) {
            return;
        }
        if (persistantSelect) {
            g.setColor(insidePers);
            g.fillRect(x, y, w, h);
            g.setColor(outlinePers);
            g.drawRect(x, y, w, h);
        } else {
            g.setColor(inside);
            g.fillRect(x, y, w, h);
            g.setColor(outline);
            g.drawRect(x, y, w, h);
        }
    }

    public int getSaveX() {
        return saveX;
    }

    public int getSaveY() {
        return saveY;
    }

    public int getSaveW() {
        return saveW;
    }

    public int getSaveH() {
        return saveH;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int mX = e.getX();
        int mY = e.getY();
        int dx = mX - x;
        int dy = mY - y;

        w = Math.abs(dx);
        h = Math.abs(dy);

        if (dx < 0) {
            x += dx;
        }
        if (dy < 0) {
            y += dy;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void setPersistantSelect(boolean persistantSelect) {
        this.persistantSelect = persistantSelect;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }
}
