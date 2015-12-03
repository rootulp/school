package hw.hw3;

public class CashRegister {

    private Cashier cashier;

    public CashRegister(Cashier c) {
        cashier = c;
    }

    public void addCustomer(int t, ItemDistribution id) {
        cashier.addCustomer(t, id);
    }

    public void elapseOneSecond(int currentTime) {
        cashier.elapseOneSecond(currentTime);
    }

    public int size() {
        return cashier.size();
    }

    public int customersServed() {
        return cashier.customersServed();
    }

    public int avgWaitTime() {
        return cashier.avgWaitTime();
    }
}
