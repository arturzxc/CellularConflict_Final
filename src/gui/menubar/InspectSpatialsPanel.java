package gui.menubar;

import controllers.Main;
import core.Cell;
import core.physics.Spatial;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Artur Krzynowek
 */
public class InspectSpatialsPanel extends JPanel {

    private List<Spatial> spatials = new ArrayList<>();
    private JComboBox spatialIds = new JComboBox();
    private JLabel sizeLbl = new JLabel();
    //Currently chosen cell properties
    private JLabel healthLbl = new JLabel();
    private JLabel wLbl = new JLabel();
    private JLabel hLbl = new JLabel();
    private JLabel cellTypeLbl = new JLabel();
    private JLabel locationLbl = new JLabel();
    private JLabel fieldofViewLbl = new JLabel();
    private JLabel maxMovesLbl = new JLabel();
    private JLabel movesLeftLbl = new JLabel();
    private JLabel thetaLbl = new JLabel();
    private JLabel maxTurnLbl = new JLabel();
    private JLabel maxSpeedLbl = new JLabel();
    private JLabel currentSpeedLbl = new JLabel();
    private JLabel separationRangeLbl = new JLabel();
    private JLabel detectionRangeLbl = new JLabel();
    private JLabel velocityLbl = new JLabel();
    private JLabel frozenLbl = new JLabel();
    private JLabel predatorOfLbl = new JLabel();
    private JLabel prayOfLbl = new JLabel();

    public InspectSpatialsPanel() {

        setLayout(new GridLayout(0, 2));
        setBorder(BorderFactory.createBevelBorder(1));

        add(new JLabel("Spatials Selected: "));
        add(sizeLbl);

        add(new JLabel("Select ID to show properties"));
        spatialIds.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                render(true, false);
            }
        });
        add(spatialIds);




        add(new JLabel("Health"));
        add(healthLbl);

        add(new JLabel("Width"));
        add(wLbl);

        add(new JLabel("Height"));
        add(hLbl);

        add(new JLabel("Type"));
        add(cellTypeLbl);

        add(new JLabel("Location"));
        add(locationLbl);

        add(new JLabel("Velocity"));
        add(velocityLbl);

        add(new JLabel("Field of View"));
        add(fieldofViewLbl);

        add(new JLabel("Max Moves"));
        add(maxMovesLbl);

        add(new JLabel("Moves Left"));
        add(movesLeftLbl);

        add(new JLabel("Max Speed"));
        add(maxSpeedLbl);

        add(new JLabel("Current Speed"));
        add(currentSpeedLbl);

        add(new JLabel("Separation Range"));
        add(separationRangeLbl);

        add(new JLabel("Detection Range"));
        add(detectionRangeLbl);

    }

    public Spatial getSpatialByID(int id) {
        for (Spatial s : spatials) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public void render(boolean self, boolean mainLoopUpdate) {
        spatials = Main.frame.getContentPane().getSelectingPanel().getSelectedSpatials();
        if (spatials.isEmpty()) {
            return;
        }
        if (!self && !mainLoopUpdate) {
            spatialIds.removeAllItems();
            for (int i = 0; i < spatials.size(); i++) {
                spatialIds.addItem(spatials.get(i).getId());
            }
        }
        if (spatialIds.getItemCount() <= 0) { //if no items in combobox then return
            return;
        }

        /*
         * This is required because there are two threads, one for the main loop
         * and one for the UI, Someties a cell is removed and we cannot get
         * information about it.
         */       
        try {
            int chosenId = (int) spatialIds.getSelectedItem();


            Spatial s = getSpatialByID(chosenId);
            sizeLbl.setText("" + spatials.size() + " Chosen id is: " + chosenId);

            if (s instanceof Cell) {
                Cell c = (Cell) s;


                healthLbl.setText("" + c.getHealth());
                wLbl.setText("" + c.getW());
                hLbl.setText("" + c.getH());
                cellTypeLbl.setText("" + c.typeToString());
                locationLbl.setText("" + c.getLocation());
                fieldofViewLbl.setText("" + c.getFieldOfView());
                movesLeftLbl.setText("" + c.getMovesLeft());
                maxMovesLbl.setText("" + c.getMaxMoves());
                maxTurnLbl.setText("" + c.getMaxTurn());
                maxSpeedLbl.setText("" + c.getMaxSpeed());
                currentSpeedLbl.setText("" + c.getCurrentSpeed());
                separationRangeLbl.setText("" + c.getSeparationRange());
                detectionRangeLbl.setText("" + c.getDetectionRange());
                velocityLbl.setText("" + c.getVelocity());

            }


        } catch (Exception exc) {
        //nothing here
        };





    }
}
