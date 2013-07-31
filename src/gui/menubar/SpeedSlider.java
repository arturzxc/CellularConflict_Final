/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menubar;

import controllers.Main;
import controllers.Settings;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Artur Krzynowek
 */
public class SpeedSlider extends JSlider implements ChangeListener {

    public SpeedSlider(int min, int max) {
        super(min, max);
        setValue(max-Settings.getInstance().getDelay());
        
        addChangeListener(this);
    }

    
    public void stateChanged(ChangeEvent e) {
        if (this.getValue() < 49) {
            Settings.getInstance().setDelay(super.getMaximum() - this.getValue());
            Main.frame.displayMsg("Current FPS is: "+Main.controller.getFPS());
        }        
    }
}
