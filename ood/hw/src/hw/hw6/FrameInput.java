package hw.hw6;

import javax.swing.*;

public class FrameInput implements WeatherInput {
    private WeatherMgr mgr;
    private ComponentStyler styler;

    public FrameInput(WeatherMgr mgr, ComponentStyler styler) {
        this.mgr = mgr;
        this.styler = styler;
    }

    public void run() {
        new CmdFrame(mgr, styler);
    }
}

@SuppressWarnings("serial")
class CmdFrame extends JFrame {
    public CmdFrame(WeatherMgr mgr, ComponentStyler styler) {
        super("Manage Weather Input");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new CmdPanel(mgr, styler));
        pack();
        setVisible(true);
    }

    class CmdPanel extends JPanel {
        public JTextField city;
        public JTextField temp;
        public JTextField pressure;
        public JTextField humidity;
        public JButton btn;

        public CmdPanel(WeatherMgr mgr, ComponentStyler styler) {

            JPanel p1 = new JPanel();
            p1.add(styler.createJLabel("City:"));
            city = styler.createJTextField(8);
            p1.add(city);

            JPanel p2 = new JPanel();
            p2.add(styler.createJLabel("Temp:"));
            temp = styler.createJTextField(8);
            p2.add(temp);

            JPanel p3 = new JPanel();
            p3.add(styler.createJLabel("Pressure:"));
            pressure = styler.createJTextField(8);
            p3.add(pressure);

            JPanel p4 = new JPanel();
            p4.add(styler.createJLabel("Humidity:"));
            humidity = styler.createJTextField(8);
            p4.add(humidity);

            JPanel p5 = new JPanel();
            btn = styler.createJButton("NEW MEASUREMENT");
            p5.add(btn);
            add(p1);
            add(p2);
            add(p3);
            add(p4);
            add(p5);

            btn.addActionListener((evt) -> {
                float stemp = Float.parseFloat(temp.getText());
                float shumid = Float.parseFloat(humidity.getText());
                float spress = Float.parseFloat(pressure.getText());
                mgr.newMeasurement(city.getText(), stemp, shumid, spress);
            });
        }
    }
}
