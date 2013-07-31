/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menubar;

import controllers.Main;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Artur Krzynowek
 */
public class ConsoleDialog extends JDialog {

    private JPanel contentPane;
    private ArrayList<String> logs = new ArrayList<>();

    public ConsoleDialog() {
        setTitle("Debugging Console");
        if (Main.getTempLogs() != null) {
            logs = Main.getTempLogs();
        }
        logs.add(0, "CONSOLE OUTPUT:");
        setResizable(false);

        contentPane = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                int logsToShow = 50;
                //show only last 10 logs
                int start = 0;
                if (logs.size() > logsToShow + 1) {
                    start = logs.size() - logsToShow;
                }
                int counter = 0;
                for (int i = start; i < logs.size(); i++) {
                    g.drawString(logs.get(i), 5, counter * 12 + 20);
                    counter++;
                }
                setTitle("Debugging Console - Currently storing " + (logs.size() - 1) + " logs (MAX 100)");
            }
        };
        this.add(contentPane);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setSize(500, 700);
        setVisible(false);
        setAlwaysOnTop(true);
    }

    public void addLog(String log) {
        //Max 1000 logs
        if (logs.size() > 100) {
            logs.remove(0);
        }
        logs.add(log);
    }

    public void render() {
        if (isVisible()) {
            repaint();
        }
    }
}
