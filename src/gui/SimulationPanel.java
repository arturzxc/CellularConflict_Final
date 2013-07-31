package gui;

import controllers.Main;
import controllers.MainController;
import controllers.Settings;
import core.*;
import core.physics.Physics;
import core.physics.Point2D;
import core.physics.Spatial;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import utils.ResourceLoader;

/**
 *
 * @author Artur Krzynowek
 */
public class SimulationPanel extends JLayeredPane implements MouseListener {

    private int ticker = 0;
    private int ticker2 = 0;
    private boolean isIncrement = true;
    private Core core;
    private CellFactory cells;
    private ObstacleFactory obstacles;
    private MainController mainController;
    private int extra = 1;
    private boolean drawRings = false;
    private boolean drawDestinationLine = false;
    private boolean drawViewCone = false;
    private Physics physics;
    private MessagePanel messagePanel = new MessagePanel();
    private SelectingPanel selectingPanel = new SelectingPanel();

    public SimulationPanel() {
        setLayout(new OverlayLayout(this));
        add(messagePanel, 1);
        add(selectingPanel, 2);
        this.physics = Physics.getInstance();
        this.mainController = Main.controller;
        this.core = Main.core;
        this.cells = core.getCellList();
        this.obstacles = core.getObstacleList();
        requestFocusInWindow();
        addMouseListener(this);
    }

    private void drawSpatials(Graphics2D g2d) {
        //DRAW SPATIALS
        for (int i = 0; i < physics.size(); i++) {
            Spatial spatial = physics.get(i);
            spatial.simpleDraw(g2d);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        Settings.getInstance().setGridWidth(getWidth());
        Settings.getInstance().setGridHeight(getHeight());

        if (ticker >= 240) {
            extra = -1;
        } else if (ticker <= 0) {
            extra = 1;
        }
        ticker += extra;
        if (ticker2 < 255) {
            ticker2++;
        } else {
            ticker2 = 0;
        }

        setHintsDrawBG(g2d);
        drawViruses(g2d);
        drawSpatials(g2d);
    }

    private void setHintsDrawBG(Graphics2D g2d) {
        //some background

        g2d.setColor(new Color(40, 40, 40));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.drawImage(
                ResourceLoader.loadImageIcon("bg2.jpg").getImage(),
                0, 0,
                getWidth(), getHeight(),
                this);


        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);

    }

    public static Point2D pointOnCircle(float radius, float angleInDegrees, Point2D origin) {
        float x = (float) (origin.x + (radius * Math.cos(angleInDegrees)));
        float y = (float) (origin.y + (radius * Math.sin(angleInDegrees)));
        return new Point2D(x, y);
    }

    //<editor-fold defaultstate="collapsed" desc="comment">
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>

    private void drawViruses(Graphics2D g2d) {
        VirusFactory vf = Main.core.getVirusFactory();
        for (int i = 0; i < vf.size(); i++) {
            Virus v = vf.get(i);

            g2d.setColor(new Color(20, 120, 23, 160));
            g2d.fillOval((int) v.getLocation().x, (int) v.getLocation().y, v.getRadius() * 2, v.getRadius() * 2);
            Image img = ResourceLoader.loadImageIcon("virus.png").getImage();
            g2d.drawImage(img,
                    (int) v.getLocation().x,
                    (int) v.getLocation().y,
                    v.getRadius() * 2,
                    v.getRadius() * 2,
                    this);
        }
    }

    public SelectingPanel getSelectingPanel() {
        return selectingPanel;
    }

    public MessagePanel getMessagePanel() {
        return messagePanel;
    }

    public boolean isDrawRings() {
        return drawRings;
    }

    public boolean isDrawDestinationLine() {
        return drawDestinationLine;
    }

    public boolean isDrawViewCone() {
        return drawViewCone;
    }

    public void toggleDrawRings() {
        drawRings = !drawRings;
    }
}
