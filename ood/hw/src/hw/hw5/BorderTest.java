package hw.hw5;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

public class BorderTest {
    public static void main(String[] args) {
        GUIFrame1 frame = new GUIFrame1();
        frame.pack();
    }
}

@SuppressWarnings("serial")
class GUIFrame1 extends JFrame {
    private GUIPanel1 panel;

    public GUIFrame1() {
        setTitle("GUI1 Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 200);
        panel = new GUIPanel1();
        setContentPane(panel);
        setVisible(true);
    }
}

@SuppressWarnings("serial")
class GUIPanel1 extends JPanel {

    public GUIPanel1() {
        Border red = (BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
        Border green = (BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
        Border blue = (BorderFactory.createMatteBorder(5, 5, 5, 5, Color.blue));

        Border redGreen = BorderFactory.createCompoundBorder(red, green);
        Border redGreenBlue = BorderFactory.createCompoundBorder(redGreen, blue);

        Border greenTitle = BorderFactory.createTitledBorder(green, "A Title");
        Border greenTitleRed = BorderFactory.createCompoundBorder(greenTitle, red);

        Border redGreenTitle = BorderFactory.createTitledBorder(redGreen, "A Title");

        JTextField txt1 = new JTextField("your message here 1", 40);
        txt1.setBorder(redGreen);

        JTextField txt2 = new JTextField("your message here 2", 40);
        txt2.setBorder(redGreenBlue);

        JTextField txt3 = new JTextField("your message here 3", 40);
        txt3.setBorder(greenTitleRed);

        JTextField txt4 = new JTextField("your message here 4", 40);
        txt4.setBorder(redGreenTitle);

        add(new JLabel("A"));
        add(txt1);
        add(new JLabel("B"));
        add(txt2);
        add(new JLabel("C"));
        add(txt3);
        add(new JLabel("D"));
        add(txt4);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

}
