
package gui.menubar;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author @Artur Krzynowek
 */
public class GraphDialog extends JDialog {
    
    private GraphPanel statisticsPanel;

    public GraphDialog() {
        setTitle("Graph");
        setPreferredSize(new Dimension(700,260));
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocation(0, 100);
        
        statisticsPanel = new GraphPanel();
        add(statisticsPanel);
        pack();        
        
    }

    /*
     * Delegate merthod
     */
    public void render(){
        statisticsPanel.render();
    }
    
    public GraphPanel getStatisticsPanel() {
        return statisticsPanel;
    }
    
    
    
    
}
