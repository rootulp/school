package hw.hw3;

public class DiningHall {

	private static final int CUST_ARRIVAL_PCT = 19;    // There is a 19% chance a customer arrives each second.
	private int NUM_NORMAL_REGISTERS;
	private int NUM_FAST_REGISTERS;
	private int NUM_TOTAL_REGISTERS;
	private ItemDistribution id;
	private CashRegister[] registers;

	public DiningHall(int numNormal, int numFast, ItemDistribution item) {
		NUM_NORMAL_REGISTERS = numNormal;
		NUM_FAST_REGISTERS = numFast;
 		NUM_TOTAL_REGISTERS = numNormal + numFast;
		id = item;
		registers = new CashRegister[NUM_TOTAL_REGISTERS];

		for (int r = 0; r < NUM_NORMAL_REGISTERS; r++) {
			Cashier normal = new NormalSpeed();
			registers[r] = new CashRegister(normal);
		}
		for (int r = NUM_NORMAL_REGISTERS; r < NUM_TOTAL_REGISTERS; r++) {
			Cashier fast = new Fast();
			registers[r] = new CashRegister(fast);
		}
	}

	public void elapseOneSecond(int t) {
		if (aCustomerArrives()) {
			// The new customer goes into the smaller line.
			int chosenRegister = shortestRegisterLine();
			registers[chosenRegister].addCustomer(t, id);
		}
		for (int r = 0; r < NUM_TOTAL_REGISTERS; r++)
			registers[r].elapseOneSecond(t);  // Simulate each register for one second.
	}

	public void printStatistics() {
		for (int r = 0; r < NUM_TOTAL_REGISTERS; r++) {
			CashRegister reg = registers[r];
			System.out.println("Register " + r);
			System.out.println("\tNumber of arrivals = " + reg.customersServed());
			System.out.println("\tAverage wait time = " + reg.avgWaitTime());
		}
	}

	private boolean aCustomerArrives() {
		int n = (int) (Math.random() * 100);  // an integer between 0 and 99
		return n < CUST_ARRIVAL_PCT;
	}

	private int shortestRegisterLine() {
		int shortestRegister = 0;

		for (int r = 0; r < NUM_TOTAL_REGISTERS; r++) {
			//System.out.println("register: " + r + " size: " + registers[r].size());
			if (registers[r].size() < registers[shortestRegister].size()) {
				shortestRegister = r;
			}
		}

		return shortestRegister;
	}
}
