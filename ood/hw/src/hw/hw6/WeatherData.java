package hw.hw6;

import java.util.*;

public class WeatherData {
    private Collection<WeatherDisplay> observers = new ArrayList<WeatherDisplay>();
    private float temperature;
    private float humidity;
    private float pressure;

    // city added for hw4
    private String city;

    public WeatherData(String city, WeatherMgr mgr) {
        this.city = city;
        mgr.addSource(this);
    }

    public void registerObserver(WeatherDisplay obs) {
        observers.add(obs);
    }

    public void notifyObservers() {
        for (WeatherDisplay wd : observers)
            wd.update(this);
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(String city, float temperature, float humidity, float pressure) {
        if (city.equals(this.city)) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            measurementsChanged();
        }
    }

    public String getCity() {
        return city;
    }

    public float getTemp() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }
}
