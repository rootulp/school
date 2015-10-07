package hw.hw4;

import java.util.Arrays;

public class HW4Weather {

    public static void main(String[] args) {
        WeatherMgr mgr = new WeatherMgr();

        WeatherData d1 = new WeatherData("Boston", mgr);
        WeatherData d2 = new WeatherData("Detroit", mgr);
        WeatherData d3 = new WeatherData("Miami", mgr);

        WeatherDisplay dp1 = new CurrentConditionsDisplay(d1);
        WeatherDisplay dp2 = new CurrentConditionsDisplay(d2);
        WeatherDisplay dp3 = new CurrentConditionsDisplay(d3);
        WeatherDisplay dp4 = new ForecastDisplay(d1);
        WeatherDisplay dp5 = new StatisticsDisplay(d2);

        WeatherInput wi = new FrameInput(mgr);

        WeatherOutput wo1 = new FrameOutput(Arrays.asList(dp1,dp4), "Boston Weather");
        WeatherOutput wo2 = new FrameOutput(Arrays.asList(dp2,dp5), "Detroit Weather");
        WeatherOutput wo3 = new ConsoleOutput(Arrays.asList(dp3), "Miami Weather");

        wi.run();
    }
}
