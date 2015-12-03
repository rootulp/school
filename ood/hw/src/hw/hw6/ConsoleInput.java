package hw.hw6;

import java.util.Scanner;


public class ConsoleInput implements WeatherInput {
    private WeatherMgr mgr;

    public ConsoleInput(WeatherMgr mgr) {
        this.mgr = mgr;
    }

    public void run() {
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.print("\nNew command: ");
            String city = scn.next();
            if (city.equals("quit")) {
                System.out.println("goodbye");
                scn.close();
                return;
            }
            float temp = scn.nextFloat();
            float humidity = scn.nextFloat();
            float pressure = scn.nextFloat();
            System.out.println("\tnew measurement for " + city + ": temp=" + temp + " humidity=" + humidity + " pressure=" + pressure);
            mgr.newMeasurement(city, temp, humidity, pressure);
        }
    }
}


