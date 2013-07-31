package controllers;

import core.Core;
import gui.MainFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * @author Artur Krzynowek
 */

public class Main {

    public static Core core;
    public static MainFrame frame;
    public static SimMainController controller;
    public static ArrayList<String> tempLogs = new ArrayList<>();
       
    /**
     * The main method of the program
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }



        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Settings.getInstance().setGridWidth((int) (d.width));
        Settings.getInstance().setGridHeight((int) (d.height - 25));//MENU BAR SPACE 25


        core = new Core();
        frame = new MainFrame();
        controller = new SimMainController(core, frame);
        controller.init();

    }

    /**
     * This method allows for addition of
     * logs to the console which is useful 
     * when application is running full screen
     */
    public static void log(String log) {
        if (frame != null) {
            frame.addLog(log);
        } else {
            tempLogs.add(log);
        }
    }

    /**
     * Gets temporary logs which are later added 
     * to the console when it is created.
     */
    public static ArrayList<String> getTempLogs() {
        return tempLogs;
    }
}
