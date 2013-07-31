package gui.menubar;

import controllers.Main;
import controllers.Settings;
import core.*;
import core.physics.Vector2D;
import gui.SelectingPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Artur Krzynowek
 */
public class CellDialog extends JDialog {

    private JComboBox typeCBox;
    private JComboBox predatorOfCBox;
    private JComboBox prayOfCBox;
    private JTextField diamiterTF;
    private JTextField number;
    private JTextField speedTF;
    private JTextField childrenTF;
    private JTextField reproTimeTF;
    private JTextField velXTF;
    private JTextField velYTF;
    private JTextField fovTF;
    private JTextField detectionTF;
    private JTextField separationTF;
    private JButton createCellsBtn;
    private JButton selectArea;
    private JTextField initialMovesLeft;

    public CellDialog() {

        setTitle("Create Cell");
        setLayout(new GridLayout(0, 2));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setLocation(0, 100);

        add(new JLabel("Color"));
        typeCBox = new JComboBox();
        typeCBox.addItem("Blue");
        typeCBox.addItem("Red");
        typeCBox.addItem("Yellow");
        typeCBox.addItem("Green");
        add(typeCBox);

        add(new JLabel("Predator of"));
        predatorOfCBox = new JComboBox();
        predatorOfCBox.addItem("Blue");
        predatorOfCBox.addItem("Red");
        predatorOfCBox.addItem("Yellow");
        predatorOfCBox.addItem("Green");
        add(predatorOfCBox);

        add(new JLabel("Pray of"));
        prayOfCBox = new JComboBox();
        prayOfCBox.addItem("Blue");
        prayOfCBox.addItem("Red");
        prayOfCBox.addItem("Yellow");
        prayOfCBox.addItem("Green");
        prayOfCBox.addItem("None");
        add(prayOfCBox);

        add(new JLabel("Number of cells (1 to 300)"));
        number = new JTextField("10");
        add(number);

        add(new JLabel("Speed (0.1 to 4.0)"));
        speedTF = new JTextField("1.5");
        add(speedTF);

        add(new JLabel("Diamiter (4 to 20)"));
        diamiterTF = new JTextField("10");
        add(diamiterTF);

        add(new JLabel("Max Children (0 to 5)"));
        childrenTF = new JTextField("2");
        add(childrenTF);

        add(new JLabel("Reproduction time (20 to 200)"));
        reproTimeTF = new JTextField("2");
        add(reproTimeTF);

        add(new JLabel("Initial velocity x (-1 to 1)"));
        velXTF = new JTextField("0.23");
        add(velXTF);

        add(new JLabel("Initial velocity y (-1 to 1)"));
        velYTF = new JTextField("0.12");
        add(velYTF);

        add(new JLabel("Initial moves left in that direction"));
        initialMovesLeft = new JTextField("1");
        add(initialMovesLeft);

        add(new JLabel("Field of view y (80 to 360)"));
        fovTF = new JTextField("180");
        add(fovTF);

        add(new JLabel("Detection Range (40 to 150)"));
        detectionTF = new JTextField("65");
        add(detectionTF);

        add(new JLabel("Separation Range (0 to 40)"));
        separationTF = new JTextField("20");
        add(separationTF);




        createCellsBtn = new JButton("Confirm and Create");
        createCellsBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Settings set = Settings.getInstance();
                if (!Main.frame.getContentPane().getSelectingPanel().isPersistantSelect()) {
                    Main.frame.displayMsg("You must first select area. Click button \"Select Area\"");
                    return;
                }
                Settings.getInstance().togglePaused();
                SelectingPanel sp = Main.frame.getContentPane().getSelectingPanel();

                int index = typeCBox.getSelectedIndex();




                int x = sp.getSaveX();
                int y = sp.getSaveY();
                int h = sp.getSaveH();
                int w = sp.getSaveW();

                int num;
                try {
                    num = Integer.parseInt(number.getText());
                } catch (Exception ex) {
                    Main.frame.displayMsg("Number of cells to create is an invalid integer!");
                    return;
                }

                ArrayList<Cell> generatedCells = Main.core.getCellList().generateCells(num, index, x, y, w - set.getCellWidth(), h - set.getCellHeight());
                for (int i = 0; i < generatedCells.size(); i++) {
                    Cell c = generatedCells.get(i);

                    int prayOf = prayOfCBox.getSelectedIndex();
                    int predatorOf = predatorOfCBox.getSelectedIndex();

                    c.setPrayOf(prayOf);
                    c.setPredatorOf(predatorOf);

                    float velX = Float.parseFloat(velXTF.getText());
                    float velY = Float.parseFloat(velYTF.getText());

                    Vector2D vel = new Vector2D(velX, velY);
                    vel.normalise();
                    vel.multiply(Float.parseFloat(speedTF.getText()));

                    c.setVelocity(vel);
                    c.setMovesLeft(Integer.parseInt(initialMovesLeft.getText()));

                }


                Main.frame.getContentPane().getSelectingPanel().setPersistantSelect(false);
                Settings.getInstance().togglePaused();
                dispose();
                //
            }
        });
        add(createCellsBtn);

        selectArea = new JButton("Select Area");
        selectArea.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.getContentPane().getSelectingPanel().setPersistantSelect(true);
                Main.frame.displayMsg("Now click and drag mouse over the area where you want the cells to be created!");

            }
        });
        add(selectArea);

        pack();
    }
}
