package hw.hw9;

import javax.swing.*;
import java.awt.event.*;

public class FileView extends JFrame {

    private FilePanel panel;

    public void addFileController(FileController fc) {
        this.panel = new FilePanel(fc);
        setTitle("Save");
        setSize(100, 100);
        setLocation(20, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setVisible(true);
    }
}

class FilePanel extends JPanel {

    private FileController fc;
    private JButton btn;

    public FilePanel(FileController fc) {
        this.fc = fc;
        this.btn = new JButton("SAVE AND EXIT");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fc.save();
                System.exit(0);
            }
        });

        add(btn);
    }

}
