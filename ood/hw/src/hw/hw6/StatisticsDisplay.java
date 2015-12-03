package hw.hw6;

public class StatisticsDisplay implements WeatherDisplay {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;
    private String city;
    private WeatherOutput wo;

    public StatisticsDisplay(WeatherData wd) {
        wd.registerObserver(this);
        city = wd.getCity();
    }

    public void setOutput(WeatherOutput wo) {
        this.wo = wo;
    }

    public void update(WeatherData wd) {
        float temp = wd.getTemp();
        tempSum += temp;
        numReadings++;
        if (temp > maxTemp)
            maxTemp = temp;
        if (temp < minTemp)
            minTemp = temp;
        wo.display(getMessage());
    }

    private String getMessage() {
        return city + " Avg/Max/Min temperature: "
                + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp;
    }
}
