package hw.hw6;

import java.util.Collection;

public class ConsoleOutput implements WeatherOutput {
	public ConsoleOutput(Collection<WeatherDisplay> displays, String title) {
		System.out.println(title);
		for (WeatherDisplay wd : displays)
			wd.setOutput(this);
	}
	
	public void display(String msg) {
		System.out.println(msg);
	}
}
