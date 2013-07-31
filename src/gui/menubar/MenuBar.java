/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menubar;

import controllers.Main;
import controllers.Settings;
import core.physics.Physics;
import core.physics.Spatial;
import gui.SelectingPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Artur Krzynowek
 */
public class MenuBar extends JMenuBar {

    private ConsoleDialog consoleDialog;
    private JMenuItem createConsoleItem;
    private JMenu debuggingMenu;
    private JMenuItem createInspectSpatialsItem;
    private JMenuItem do10FramesStop;
    private JMenu statisticsMenu;
    private JMenu mainMenu;
    private JMenu optionsMenu;
    private JMenu spatialMenu;
    private JMenuItem createCellItem;
    private JMenuItem createObstacleItem;
    private JMenuItem createVirusItem;
    private JMenuItem exitItem;
    private JMenuItem createGraphItem;
    private JMenuItem removeEverythingItem;
    private JMenuItem removeSelectedItem;
    private JMenuItem startItem;
    private GraphDialog graphDialog = new GraphDialog();
    private CellDialog cellDialog = new CellDialog();
    private VirusDialog virusDialog = new VirusDialog();
    private InspectSpatialDialog inspectSpatialDialog = new InspectSpatialDialog();
    private SpeedSlider speedSlider = new SpeedSlider(1, 50);

    public ConsoleDialog getConsoleDialog() {
        return consoleDialog;
    }

    public MenuBar() {
        compose();
        setAccelerators();
        addListeners();
    }

    private void compose() {
        //MAIN MENU-------------------------------------------------------------
        mainMenu = new JMenu("Cellular Conflict");
        startItem = new JMenuItem("Start/Pause");
        exitItem = new JMenuItem("Exit");
        mainMenu.add(startItem);
        mainMenu.addSeparator();
        mainMenu.add(exitItem);

        //SPATIAL MENU----------------------------------------------------------
        spatialMenu = new JMenu("Spatials");
        removeSelectedItem = new JMenuItem("Remove Selected");
        removeEverythingItem = new JMenuItem("Remove Everything");
        createCellItem = new JMenuItem("Create Cells...");
        createVirusItem = new JMenuItem("Create Virus...");
        createObstacleItem = new JMenuItem("Create Obstacle...");
        spatialMenu.add(removeSelectedItem);
        spatialMenu.add(removeEverythingItem);
        spatialMenu.addSeparator();
        spatialMenu.add(createCellItem);
        spatialMenu.add(createVirusItem);
        spatialMenu.add(createObstacleItem);

        //STATISTICS MENU-------------------------------------------------------
        statisticsMenu = new JMenu("Statistics");
        createGraphItem = new JMenuItem("View Graph");
        statisticsMenu.add(createGraphItem);

        //OPTIONS MENU----------------------------------------------------------
        optionsMenu = new JMenu("Options");

        //DEBUGGING MENU-------------------------------------------------------
        debuggingMenu = new JMenu("Debugging");
        createInspectSpatialsItem = new JMenuItem("Inspect Spatials...");
        createConsoleItem = new JMenuItem("Console...");
        consoleDialog = new ConsoleDialog();
        
        debuggingMenu.add(createConsoleItem);
        debuggingMenu.add(createInspectSpatialsItem);
        
        //COMPOSE ALL-----------------------------------------------------------
        add(mainMenu);
        add(spatialMenu);
        add(statisticsMenu);
        add(optionsMenu);
        add(debuggingMenu);
        add(speedSlider);

    }

    private void addListeners() {
        createCellItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cellDialog.setVisible(true);
                Main.frame.displayMsg("Set the properties and then click Select Area to specidy where the newly created cells will be created");
            }
        });

        createVirusItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                virusDialog.setVisible(true);
                Main.frame.displayMsg("Create virus");
            }
        });
        createGraphItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                graphDialog.setVisible(true);
                Main.frame.displayMsg("Real-Time view of spatial numbers");
            }
        });
        startItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.getInstance().togglePaused();
                if (Settings.getInstance().isPaused()) {
                    Main.frame.displayMsg("Simulation paused");
                } else {
                    Main.frame.displayMsg("Simulation resumed");
                }
            }
        });
        removeEverythingItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Spatial> spatials = Physics.getInstance();
                for (int i = spatials.size() - 1; i >= 0; i--) {
                    spatials.get(i).delete();
                }
                Main.frame.displayMsg("All spatials removed");
            }
        });
        removeSelectedItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SelectingPanel selectingPanel = Main.frame.getContentPane().getSelectingPanel();
                List<Spatial> spatials = selectingPanel.getSelectedSpatials();
                int initSize = spatials.size();
                for (int i = spatials.size() - 1; i >= 0; i--) {
                    spatials.get(i).delete();
                }
                Main.frame.displayMsg("Removed spatials: " + initSize);
            }
        });
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        createInspectSpatialsItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                inspectSpatialDialog.setVisible(true);
                Main.frame.displayMsg("Select spatials using mouse click and drag");
            }
        });
        createConsoleItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                consoleDialog.setVisible(true);
                Main.frame.displayMsg("Showing Console...");
            }
        });
        

    }

    private void setAccelerators() {
        startItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_SPACE, ActionEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_ESCAPE, ActionEvent.SHIFT_MASK));
        removeSelectedItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        removeEverythingItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, ActionEvent.SHIFT_MASK));
        createCellItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.ALT_MASK));
        createVirusItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.ALT_MASK));
        createObstacleItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        createGraphItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.ALT_MASK));
        createInspectSpatialsItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.ALT_MASK));
        createConsoleItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.SHIFT_MASK));
    }

    public GraphDialog getGraphDialog() {
        return graphDialog;
    }

    public InspectSpatialDialog getInspectSpatialDialog() {
        return inspectSpatialDialog;
    }
}
