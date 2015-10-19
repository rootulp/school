package hw.hw5;

public abstract class AnalystDecorator extends StockAnalyst {

    private StockAnalyst component;

    public AnalystDecorator(StockAnalyst component) {
        super(component.getStockInfo());
        this.component = component;
    }

    protected StockAnalyst getComponent() {
        return component;
    }

    public abstract double confidenceLevel();
    public abstract String reasons();
}
