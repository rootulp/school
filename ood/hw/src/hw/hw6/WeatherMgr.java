package hw.hw6;

import java.util.*;

public class WeatherMgr {
    private Collection<WeatherData> sources = new ArrayList<>();

    public void addSource(WeatherData wd) {
        sources.add(wd);
    }

    public void newMeasurement(String city, float temperature, float humidity, float pressure) {
        for (WeatherData wd : sources)
            wd.setMeasurements(city, temperature, humidity, pressure);
    }
}
