package hw.hw4;

import java.util.*;

public class WeatherData {
    private Collection<WeatherDisplay> observers = new ArrayList<WeatherDisplay>();
    private float temperature;
    private float humidity;
    private float pressure;
    private String city;

    public WeatherData(String c, WeatherMgr wm) {
        city = c;
        wm.registerObserver(this);
    }

    public void registerObserver(WeatherDisplay obs) {
        observers.add(obs);
    }

    public void removeObserver(WeatherDisplay obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for (WeatherDisplay wd : observers)
            wd.update(this);
    }

    public float getTemp() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(String city, float temperature, float humidity, float pressure) {
        if (this.city.equals(city)) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            measurementsChanged();
        }
    }
}
