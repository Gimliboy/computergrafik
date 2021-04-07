package script.demos.java2d; /**
 *Beispielprogramm zum "Lehrbuch der Grafikprogrammierung"
 *Prof. Dr. Klaus Zeppenfeld
 *(c) 2003 Prof. Dr. Klaus Zeppenfeld
 *@author  Regine Wolters, Prof. Dr. Klaus Zeppenfeld
 *@version 1.0
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class ColorChooserDemo extends JPanel
                              implements ChangeListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JColorChooser jcc;
    protected JLabel ausgabe;

    public ColorChooserDemo() {
        ausgabe = new JLabel();
        ausgabe.setForeground(Color.yellow);
        ausgabe.setBackground(Color.blue);
        ausgabe.setOpaque(true);
        ausgabe.setPreferredSize(new Dimension(100, 65));
        
        JPanel ausgabePanel = new JPanel(new BorderLayout());
        ausgabePanel.add(ausgabe, BorderLayout.CENTER);
        ausgabePanel.setBorder(BorderFactory.createTitledBorder("Ausgabe"));
        
        jcc = new JColorChooser(ausgabe.getBackground());
        jcc.getSelectionModel().addChangeListener(this);
        jcc.setBorder(BorderFactory.createTitledBorder(
                                             "Auswahl Farbmodell"));

        setLayout(new BorderLayout());
        add(ausgabePanel, BorderLayout.CENTER);
        add(jcc, BorderLayout.PAGE_END);
    }

    public void stateChanged(ChangeEvent e) {
        Color newColor = jcc.getColor();
        ausgabe.setBackground(newColor);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Farbmodelle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ColorChooserDemo());

        frame.pack();
        frame.setVisible(true);
    }
}
