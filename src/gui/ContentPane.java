
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import static utils.LayoutHelper.changeConstraints;
import static utils.LayoutHelper.setUpConstraints;

/**
 *
 * @author Artur Krzynowek
 */
public class ContentPane extends JPanel {

    private GridBagLayout layout;
    private GridBagConstraints cons;
    private SimulationPanel simulationPanel;


    public SimulationPanel getSimulationPanel() {
        return simulationPanel;
    }

    public SelectingPanel getSelectingPanel() {
        return simulationPanel.getSelectingPanel();
    }

    public ContentPane() {        
        layout = new GridBagLayout();
        cons = new GridBagConstraints();
        setUpConstraints(cons);
        setLayout(layout);
        simulationPanel = new SimulationPanel();
        changeConstraints(cons, 1, 0, 1, 1, 1, 1);
        add(simulationPanel, cons);
    }
}
