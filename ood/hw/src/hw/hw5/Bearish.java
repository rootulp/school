package hw.hw5;

public class Bearish extends StockAnalyst {

    public Bearish(StockInfo si) {
        super(si);
    }

    public double confidenceLevel() {
        return 0.40;
    }

    public String reasons() {
        return "Bear Market";
    }

}

