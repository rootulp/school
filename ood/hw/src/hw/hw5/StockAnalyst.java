package hw.hw5;

public abstract class StockAnalyst {

    private StockInfo si;

    public StockAnalyst(StockInfo si) {
        this.si = si;
    }

    protected StockInfo getStockInfo() {
        return si;
    }

    public abstract double confidenceLevel();

    public abstract String reasons();

}
