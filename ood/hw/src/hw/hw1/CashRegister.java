package hw.hw1;

import java.util.List;
import java.util.LinkedList;

public class CashRegister {

    private List<Integer> arrivalTimes;
    private List<Integer> serviceTimes;
    private int customersServed;
    private int totalWaitTimes;

    public CashRegister() {
        this.arrivalTimes = new LinkedList<Integer>();
        this.serviceTimes =  new LinkedList<Integer>();
        this.customersServed = 0;
        this.totalWaitTimes = 0;
    }

    public int lineLength() {
        return arrivalTimes.size();
    }

    public void addToLine(Customer currentCustomer, int t) {
        arrivalTimes.add(t);
        serviceTimes.add(currentCustomer.getNumItems() + 5);
    }

    public int getCustomersServed() {
        return customersServed;
    }

    public int getAverageWaitTime() {
        return (totalWaitTimes / customersServed);
    }

    public void processCustomer(int currentTime) {
        if (arrivalTimes.size() == 0)
            return;

        int timeLeft = serviceTimes.get(0) - 1;

        if (timeLeft > 0) {
            serviceTimes.set(0, timeLeft);
        } else {
            customersServed++;
            totalWaitTimes += currentTime - arrivalTimes.get(0);
            arrivalTimes.remove(0);
            serviceTimes.remove(0);
        }

    }

}
