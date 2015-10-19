package hw.hw5;

public class Bullish extends StockAnalyst {

    public Bullish(StockInfo si) {
        super(si);
    }

    public double confidenceLevel() {
        return 0.60;
    }

    public String reasons() {
        return "Bull Market";
    }

}
