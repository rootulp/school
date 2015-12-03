package hw.hw5;

public class PERatioAnalyst extends AnalystDecorator {

    public PERatioAnalyst(StockAnalyst component) {
        super(component);
    }

    public String reasons() {
        return getComponent().reasons() + " and PERatio Analyzed";
    }

    public double confidenceLevel() {
        return (getComponent().confidenceLevel() + priceEarningsConfidenceLevel()) / 2;
    }

    private double priceEarningsConfidenceLevel() {
        double cl = priceEarningsRatio() / 24;

        if (cl < .25) {
            cl = .25;
        } // If confidence level is below 25%, set it to 25%
        if (cl > .75) {
            cl = .75;
        } // If confidence level is above 75%, set it to 75%

        return cl;
    }

    private double priceEarningsRatio() {
        return Double.parseDouble(getComponent().getStockInfo().get("shareprice")) /
                Double.parseDouble(getComponent().getStockInfo().get("earnings"));
    }

}
