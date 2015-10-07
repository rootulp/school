package hw.hw3;

import java.util.*;

public abstract class Cashier {
    protected List<Customer> custs = new LinkedList<Customer>();
    protected int numServed = 0;
    protected int totalWaitTime = 0;
    protected Customer currentCust = null;

    public void addCustomer(int t, ItemDistribution id) {
        Customer c = new Customer(t, id);
        custs.add(c);
        if (currentCust == null)
            currentCust = c;
    }


    public int size() {
        return custs.size();
    }

    public int customersServed() {
        return numServed;
    }

    public int avgWaitTime() {
        if (numServed != 0) {
            return totalWaitTime / numServed;
        } else {
            return 0;
        }
    }

    public abstract void elapseOneSecond(int currentTime);
}

class Fast extends Cashier {

    public void elapseOneSecond(int currentTime) {
        // Return if there is no current customer.
        if (currentCust == null)
            return;

        // Otherwise, process the current customer.
        currentCust.elapseOneSecond();
        if (currentCust.isFinished()) {
            numServed++;
            totalWaitTime += currentTime - currentCust.arrivalTime();

            // Remove the finished customer and make the next customer current.
            custs.remove(0);
            currentCust = custs.size() > 0 ? custs.get(0) : null;
            return;
        }

        currentCust.elapseOneSecond();
        if (currentCust.isFinished()) {
            numServed++;
            totalWaitTime += currentTime - currentCust.arrivalTime();

            // Remove the finished customer and make the next customer current.
            custs.remove(0);
            currentCust = custs.size() > 0 ? custs.get(0) : null;
            return;
        }

    }
}

class NormalSpeed extends Cashier {

    public void elapseOneSecond(int currentTime) {
        // Return if there is no current customer.
        if (currentCust == null)
            return;

        // Otherwise, process the current customer.
        currentCust.elapseOneSecond();
        if (currentCust.isFinished()) {
            numServed++;
            totalWaitTime += currentTime - currentCust.arrivalTime();

            // Remove the finished customer and make the next customer current.
            custs.remove(0);
            currentCust = custs.size() > 0 ? custs.get(0) : null;
        }
    }
}