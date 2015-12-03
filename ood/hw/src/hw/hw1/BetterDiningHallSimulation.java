package hw.hw1;

public class BetterDiningHallSimulation {

    private static final int SIMULATION_TIME = 10000;
    private static final int CUST_ARRIVAL_PCT = 19;

    private static final CashRegister register0 = new CashRegister();
    private static final CashRegister register1 = new CashRegister();

    public static void main(String[] args) {

        for (int t = 0; t < SIMULATION_TIME; t++) {
            if (aCustomerArrives()) {
                Customer currentCustomer = new Customer(howManyItems());

                if (register0.lineLength() < register1.lineLength()) {
                    register0.addToLine(currentCustomer, t);
                } else {
                    register1.addToLine(currentCustomer, t);
                }
            }

            elapseOneSecond(t);
        }

        System.out.println("Register 0");
        System.out.println("\tNumber of arrivals = " + register0.getCustomersServed());
        System.out.println("\tAverage wait time = " + register0.getAverageWaitTime());
        System.out.println("Register 1");
        System.out.println("\tNumber of arrivals = " + register1.getCustomersServed());
        System.out.println("\tAverage wait time = " + register1.getAverageWaitTime());
    }

    private static boolean aCustomerArrives() {
        int n = (int) (Math.random() * 100);
        return n < CUST_ARRIVAL_PCT;
    }

    private static void elapseOneSecond(int currentTime) {
        register0.processCustomer(currentTime);
        register1.processCustomer(currentTime);
    }

    private static int howManyItems() {
        int n = (int) (Math.random() * 10);
        return n + 1;
    }

}
