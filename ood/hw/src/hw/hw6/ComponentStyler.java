package hw.hw6;

import java.awt.Color;
import javax.swing.*;

public class ComponentStyler {

    private Color c1;
    private Color c2;

    public ComponentStyler(Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public JButton createJButton(String text) {
        JButton button = new JButton(text);
        button.setBorder(BorderFactory.createLineBorder(c1, 5));
        button.setForeground(c2);

        return button;
    }

    public JLabel createJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(c1);

        return label;
    }

    public JTextField createJTextField(int size) {
        JTextField textField = new JTextField(size);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createLineBorder(c1, 5));
        textField.setBackground(c2);

        return textField;
    }

    public JTextArea createJTextArea(int x, int y) {
        JTextArea textArea = new JTextArea(x, y);
        textArea.setOpaque(true);
        textArea.setBorder(BorderFactory.createLineBorder(c1, 5));
        textArea.setBackground(c2);

        return textArea;
    }

}
