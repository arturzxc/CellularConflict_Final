package gui;

import controllers.Settings;
import gui.menubar.MenuBar;
import java.awt.Color;
import javax.swing.JFrame;
import utils.ResourceLoader;

/**
 *
 * @author Artur Krzynowek
 */
public class MainFrame extends JFrame {

    private final int pwidth;
    private final int pheight;
    private final ContentPane contentPane;
    private MenuBar myMenuBar;
    
    
    public MainFrame() {
        setFocusable(true);
        setIconImage(ResourceLoader.loadImageIcon("icon.png").getImage());
        pwidth = Settings.getInstance().getwWidth() + 10;
        pheight = Settings.getInstance().getwHeight() + 30;
        setSize(pwidth, pheight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cellular Conflict");
        setResizable(false);
        setUndecorated(true);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setBackground(Color.gray);
        myMenuBar = new MenuBar();
        setJMenuBar(myMenuBar);
        contentPane = new ContentPane();
        
        setContentPane(contentPane);
        setVisible(true);
    }
    
    public void addLog(String log){
        myMenuBar.getConsoleDialog().addLog(log);
    }

    @Override
    public ContentPane getContentPane() {
        return contentPane;
    }

    public void displayMsg(String str) {
        contentPane.getSimulationPanel().getMessagePanel().displayMsg(str);
    }

    public void render() {
        contentPane.getSimulationPanel().repaint();
        myMenuBar.getGraphDialog().render();
        myMenuBar.getInspectSpatialDialog().render(false, true);
        myMenuBar.getConsoleDialog().render();
    }


    public MenuBar getMyMenuBar() {
        return myMenuBar;
    }
}
