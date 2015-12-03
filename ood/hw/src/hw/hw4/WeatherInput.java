package hw.hw4;

import java.util.Scanner;
import java.awt.event.*;
import javax.swing.*;

public interface WeatherInput {
    void run();
}

class ConsoleInput implements WeatherInput {

    private static Scanner reader = new Scanner(System.in);
    private WeatherMgr mgr;

    public ConsoleInput(WeatherMgr mgr) {
        this.mgr = mgr;
    }

    public void run() {
        while (true) {
            System.out.println("Input city (ex. 'Boston', 'Detroit', or 'Miami'):");
            String city = reader.nextLine();

            System.out.println("Input temperature:");
            float temperature = reader.nextFloat();

            System.out.println("Input humidity:");
            float humidity = reader.nextInt();

            System.out.println("Input pressure:");
            float pressure = reader.nextInt();

            String garbage = reader.nextLine();

            mgr.notifyObservers(city, temperature, humidity, pressure);
        }
    }

}

class FrameInput implements WeatherInput {

    private WeatherMgr mgr;

    public FrameInput(WeatherMgr mgr) {
        this.mgr = mgr;
    }

    public void run() {
        GUIFrame5 frame = new GUIFrame5();
        frame.pack();
    }

    @SuppressWarnings("serial")
    class GUIFrame5 extends JFrame {
        private GUIPanel5 panel;

        public GUIFrame5() {
            setTitle("Frame Input");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(200, 200);
            panel = new GUIPanel5();
            setContentPane(panel);
            setVisible(true);
        }
    }

    @SuppressWarnings("serial")
    class GUIPanel5 extends JPanel {
        private JLabel lbl1 = new JLabel("City (Boston, Detroit, Miami): ");
        private JTextField txt1 = new JTextField("Boston", 4);
        private JLabel lbl2 = new JLabel("Temperature: ");
        private JTextField txt2 = new JTextField("75", 4);
        private JLabel lbl3 = new JLabel("Humidity: ");
        private JTextField txt3 = new JTextField("50", 4);
        private JLabel lbl4 = new JLabel("Pressure: ");
        private JTextField txt4 = new JTextField("40", 4);
        private JButton btn = new JButton("Submit");

        public GUIPanel5() {
            JPanel p1 = new JPanel();

            p1.add(lbl1);
            p1.add(txt1);

            p1.add(lbl2);
            p1.add(txt2);

            p1.add(lbl3);
            p1.add(txt3);

            p1.add(lbl4);
            p1.add(txt4);

            add(p1);

            add(btn);
            btn.addActionListener(new TextFieldListener(this));
        }

    }

    class TextFieldListener implements ActionListener {
        private GUIPanel5 pnl;

        public TextFieldListener(GUIPanel5 pnl) {
            this.pnl = pnl;
        }

        public void actionPerformed(ActionEvent evt) {
            float temperature = 0;
            float humidity = 0;
            float pressure = 0;

            String city = pnl.txt1.getText();
            String t = pnl.txt2.getText();
            String h = pnl.txt3.getText();
            String p = pnl.txt4.getText();

            try {
                temperature = Float.parseFloat(t);
            } catch (NumberFormatException e) {
            }
            try {
                humidity = Float.parseFloat(h);
            } catch (NumberFormatException e) {
            }
            try {
                pressure = Float.parseFloat(p);
            } catch (NumberFormatException e) {
            }

            mgr.notifyObservers(city, temperature, humidity, pressure);
        }
    }
}
