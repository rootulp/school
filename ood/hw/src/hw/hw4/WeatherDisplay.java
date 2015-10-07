package hw.hw4;

import java.util.*;

public abstract class WeatherDisplay {
    protected Collection<WeatherOutput> observers = new ArrayList<WeatherOutput>();

    public void registerObserver(WeatherOutput obs) {
        observers.add(obs);
    }

    public void notifyObservers(String displayString) {
        for (WeatherOutput wo : observers)
            wo.display(displayString);
    }

    abstract void update(WeatherData wd);
    abstract String displayString();
}

class CurrentConditionsDisplay extends WeatherDisplay {
    private float temp;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData wd) {
        wd.registerObserver(this);
    }

    public void update(WeatherData wd) {
        this.temp = wd.getTemp();
        this.humidity = wd.getHumidity();
        notifyObservers(displayString());
    }

    public String displayString() {
        return ("Current conditions: " + temp
                + "F degrees and " + humidity + "% humidity");
    }

}

class ForecastDisplay extends WeatherDisplay {
    private float currentpressure = 29.27f;
    private float oldpressure;

    public ForecastDisplay(WeatherData wd) {
        wd.registerObserver(this);
    }

    public void update(WeatherData wd) {
        oldpressure = currentpressure;
        currentpressure = wd.getPressure();
        notifyObservers(displayString());
    }

    public String displayString() {
        if (currentpressure > oldpressure)
            return ("Forecast: Improving weather on the way!");
        else if (currentpressure == oldpressure)
            return ("Forecast: More of the same");
        else
            return ("Forecast: Watch out for cooler, rainy weather");
    }
}

class StatisticsDisplay extends WeatherDisplay {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum= 0.0f;
    private int numReadings;

    public StatisticsDisplay(WeatherData wd) {
        wd.registerObserver(this);
    }

    public void update(WeatherData wd) {
        float temp = wd.getTemp();
        tempSum += temp;
        numReadings++;
        if (temp > maxTemp)
            maxTemp = temp;
        if (temp < minTemp)
            minTemp = temp;
        notifyObservers(displayString());
    }

    public String displayString() {
        return ("Avg/Max/Min temperature: " + (tempSum / numReadings)
                + "/" + maxTemp + "/" + minTemp);
    }
}
