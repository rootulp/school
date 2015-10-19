package hw.hw5;

public class SegmentAnalyst extends AnalystDecorator {

    public SegmentAnalyst(StockAnalyst component) {
        super(component);
    }

    public String reasons() {
        if (segmentConfidenceLevel() != 0) {
            return getComponent().reasons() + " and Segment Analyzed";
        } else {
            return getComponent().reasons();
        }
    }

    public double confidenceLevel() {
        if (segmentConfidenceLevel() != 0) {
            return (getComponent().confidenceLevel() + segmentConfidenceLevel()) / 2;
        } else {
            return getComponent().confidenceLevel();
        }
    }

    private double segmentConfidenceLevel() {
        String segment = getStockInfo().get("marketsegment");

        if (segment.equals("technology")) {
            return .8;
        } else if (segment.equals("auto")) {
            return .2;
        } else {
            return 0;
        }
    }


}
