package hw.hw3;

import java.util.Scanner;

public class EvenBetterDiningHallSimulation {
    private static final int SIMULATION_TIME = 10000;
    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter Number of Normal Cash Registers:");
        int numNormal = reader.nextInt();

        System.out.println("Enter Number of Fast Cash Registers:");
        int numFast = reader.nextInt();

        System.out.println("Enter distType:");
        String distType = reader.next();

        System.out.println("Enter distSize:");
        int distSize = reader.nextInt();

        ItemDistribution id;

        if (distType.equals("uniform")) {
            id = new Uniform(distSize);
        } else {
            id = new Bimodal(distSize);
        }

        DiningHall hall = new DiningHall(numNormal, numFast, id);

        for (int t = 0; t < SIMULATION_TIME; t++)
            hall.elapseOneSecond(t);

        hall.printStatistics();
    }
}
