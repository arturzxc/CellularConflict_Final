
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This is the panel that draws
 * messages in top right corner.
 * @author Artur Krzynowek
 */
public class MessagePanel extends JPanel {

    private String msg = "test";
    private int countDown = 0;
    private int length = 300;

    public MessagePanel() {
        setOpaque(false);
    }

    public void displayMsg(String msg) {
        this.msg = msg;
        this.countDown = length;
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (countDown == 0) {
            return;
        }

        int counter = 0;
        if (countDown > 240) {
            counter = 240;
        } else if (countDown > 0) {
            counter = countDown;
        }

        int fontSize = 15;

        g.setColor(new Color(220, 220, 220, counter));        
        g.fillRoundRect(5, 5, 50 + (7 * msg.length()), 45, 15, 15);

        g.setColor(new Color(60, 60, 60, counter));
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        g.drawString(msg, 26, fontSize * 2+2);

        if (countDown > 0) {
            countDown--;
        }
    }
}
