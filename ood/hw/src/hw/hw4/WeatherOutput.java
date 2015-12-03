package hw.hw4;

import java.util.*;
import javax.swing.*;


public interface WeatherOutput {
    void display(String displayString);
}

class ConsoleOutput implements WeatherOutput {

    private String title;

    ConsoleOutput(List<WeatherDisplay> wds, String t) {
        title = t;
        for (WeatherDisplay wd : wds)
            wd.registerObserver(this);
    }

    public void display(String displayString) {
        System.out.println(title + ": " + displayString);
    }

}

class FrameOutput implements WeatherOutput {

    private String title;

    FrameOutput(List<WeatherDisplay> wds, String t) {
        title = t;
        for (WeatherDisplay wd : wds)
            wd.registerObserver(this);

    }

    public void display(String displayString) {
        GUIFrame5 frame = new GUIFrame5(displayString);
        frame.pack();
    }

    @SuppressWarnings("serial")
    class GUIFrame5 extends JFrame {
        private GUIPanel5 panel;

        public GUIFrame5(String displayString) {
            setTitle(title);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(200, 200);
            panel = new GUIPanel5(displayString);
            setContentPane(panel);
            setVisible(true);
        }
    }

    @SuppressWarnings("serial")
    class GUIPanel5 extends JPanel {

        public GUIPanel5(String displayString) {
            JPanel p1 = new JPanel();
            JLabel lbl1 = new JLabel(displayString);
            p1.add(lbl1);
            add(p1);
        }

    }
}
