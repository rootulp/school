package hw.hw4;

import java.util.*;

public class WeatherMgr {
    private Collection<WeatherData> observers = new ArrayList<WeatherData>();

    public void registerObserver(WeatherData obs) {
        observers.add(obs);
    }

    public void removeObserver(WeatherDisplay obs) {
        observers.remove(obs);
    }

    public void notifyObservers(String city, float temperature, float humidity, float pressure) {
        for (WeatherData wd : observers)
            wd.setMeasurements(city, temperature, humidity, pressure);
    }

}
