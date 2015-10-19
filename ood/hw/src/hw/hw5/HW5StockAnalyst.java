package hw.hw5;

public class HW5StockAnalyst {
    public static void main(String[] args) {
        String filename = "appleInfo.txt";
        StockInfo si = new StockInfo(filename);
        StockAnalyst a = new Bullish(si);
        a = new PERatioAnalyst(a);
        a = new SegmentAnalyst(a);
        System.out.println("Confidence level is " + a.confidenceLevel());
        System.out.println("Reasoning: " + a.reasons());
    }
}
