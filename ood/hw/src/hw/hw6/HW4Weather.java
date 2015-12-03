package hw.hw6;

import java.util.Arrays;
import java.awt.Color;

public class HW4Weather {

    public static void main(String[] args) {
        ComponentStyleFactory csf = ComponentStyleFactory.instance();
        Color gold = new Color(255, 215, 0);
        Color maroon = new Color(128, 0, 0);
        ComponentStyler bc = csf.create(maroon, gold);
        ComponentStyler umich = csf.create(Color.BLUE, Color.YELLOW);
        ComponentStyler red = csf.createMonochrome(Color.RED);
        ComponentStyler green = csf.createMonochrome(Color.GREEN);

        WeatherMgr mgr = new WeatherMgr();

        WeatherData d1 = new WeatherData("Boston", mgr);
        WeatherData d2 = new WeatherData("Ann Arbor", mgr);
        WeatherData d3 = new WeatherData("Miami", mgr);

        WeatherDisplay dp1 = new CurrentConditionsDisplay(d1);
        WeatherDisplay dp2 = new CurrentConditionsDisplay(d2);
        WeatherDisplay dp3 = new CurrentConditionsDisplay(d3);
        WeatherDisplay dp4 = new ForecastDisplay(d1);
        WeatherDisplay dp5 = new StatisticsDisplay(d2);

        WeatherInput wi = new FrameInput(mgr, red);
        WeatherOutput wo1 = new FrameOutput(Arrays.asList(dp1, dp4), "Boston Weather", bc);
        WeatherOutput wo2 = new FrameOutput(Arrays.asList(dp2, dp5), "Ann Arbor Weather", umich);
        WeatherOutput wo3 = new FrameOutput(Arrays.asList(dp3), "Miami Weather", green);

        wi.run();
    }
}
