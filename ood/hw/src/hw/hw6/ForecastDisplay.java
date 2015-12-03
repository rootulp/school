package hw.hw6;

public class ForecastDisplay implements WeatherDisplay {
    private float currentpressure = 29.27f;
    private float oldpressure;
    private String city;
    private WeatherOutput wo;

    public ForecastDisplay(WeatherData wd) {
        wd.registerObserver(this);
        city = wd.getCity();
    }

    public void setOutput(WeatherOutput wo) {
        this.wo = wo;
    }

    public void update(WeatherData wd) {
        oldpressure = currentpressure;
        currentpressure = wd.getPressure();
        wo.display(getMessage());
    }

    public String getMessage() {
        String result = "Forecast for " + city + ": ";
        if (currentpressure > oldpressure)
            result += "Improving weather on the way!";
        else if (currentpressure == oldpressure)
            result += "More of the same";
        else
            result += "Watch out for cooler, rainy weather";
        return result;
    }
}
