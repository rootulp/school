/*
package hw.hw5;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

public class Border3C {
    public static void main(String[] args) {
        GUIFrame2 frame = new GUIFrame2();
        frame.pack();
    }
}

@SuppressWarnings("serial")
class GUIFrame2 extends JFrame {
    private GUIPanel2 panel;

    public GUIFrame2() {
        setTitle("GUI1 Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200,200);
        panel = new GUIPanel2();
        setContentPane(panel);
        setVisible(true);
    }
}

@SuppressWarnings("serial")
class GUIPanel2 extends JPanel {

    public GUIPanel2() {
        JTextField txt1 = new JTextField("your message here 1", 40);
        txt1 = new JTextFieldMatteBorder(txt1, 5, 5, 5, 5, Color.green));
        txt1 = new JTextFieldMatteBorder(txt1, 5, 5, 5, 5, Color.red));

        add(new JLabel("A"));
        add(txt1);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

}
*/
