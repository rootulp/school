package hw.hw6;

public class CurrentConditionsDisplay implements WeatherDisplay {
	private WeatherOutput wo;
	private float temp;
	private float humidity;
	private String city;

	public CurrentConditionsDisplay(WeatherData wd) {
		wd.registerObserver(this);
		city = wd.getCity();
	} 
	
	public void setOutput(WeatherOutput wo) {
		this.wo = wo;
	}

	public void update(WeatherData wd) {
		temp     = wd.getTemp();
		humidity = wd.getHumidity();
		wo.display(getMessage());
	}

	public String getMessage() {
		return "Current " + city + " weather: " + temp + "F degrees and " + humidity + "% humidity";
	}
}
