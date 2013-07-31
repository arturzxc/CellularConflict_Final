/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menubar;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Artur Krzynowek
 */
public class VirusDialog extends JDialog {

    public VirusDialog() {
        setTitle("Create Virus");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        setResizable(false);
        setAlwaysOnTop(true);
        setLocation(0, 100);
        add(new JLabel("Virus Color"));
        JComboBox colorCB = new JComboBox();
        colorCB.addItem("Red");
        colorCB.addItem("Blue");
        colorCB.addItem("Green");
        colorCB.addItem("Yellow");
        add(colorCB);

        add(new JLabel("Virus type"));
        JComboBox virusTypeCB = new JComboBox();
        virusTypeCB.addItem("Deadly");
        virusTypeCB.addItem("Deadly Contageus");
        virusTypeCB.addItem("Impairing");
        virusTypeCB.addItem("Impairing Contageus");
        virusTypeCB.addItem("Mutator");
        add(virusTypeCB);

        pack();
    }
}
