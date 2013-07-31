/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menubar;

import java.awt.Dimension;
import javax.swing.JDialog;

/**
 *
 * @author Artur Krzynowek
 */
public class InspectSpatialDialog extends JDialog {

    private InspectSpatialsPanel inspectCellsPanel = new InspectSpatialsPanel();
    
    public InspectSpatialDialog() {
        setTitle("Inspect Spatials");
        setPreferredSize(new Dimension(350,500));
        setLocation(0, 100);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        add(inspectCellsPanel);
        pack();
    }
    
    public void render(boolean self, boolean mainLoop){
        if(isVisible()){
            inspectCellsPanel.render(self, mainLoop);
        }
    }
}
