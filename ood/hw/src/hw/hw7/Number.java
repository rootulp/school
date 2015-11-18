package hw.hw7;

import java.util.Map;

public class Number extends ArithmeticExpression {

    private int val;

    public Number (int val) {
        this.val = val;
    }

    public int evaluate(Map<String, Integer> vars) {
        return val;
    }

    public String toString(Map<String, Integer> vars) {
        return Integer.toString(val);
    }

}
