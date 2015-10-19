package hw.hw5;

public class Neutral extends StockAnalyst {

    public Neutral(StockInfo si) {
        super(si);
    }

    public double confidenceLevel() {
        return 0.50;
    }

    public String reasons() {
        return "Neutral Market";
    }

}
