package hw.hw7;

import java.util.Map;

public class Product extends ArithmeticExpression {

    public String toString(Map<String, Integer> vars) {
        if (left == null && right == null) {
            return " * ";
        } else {
            return left.toString(vars) + " * " + right.toString(vars);
        }
    }

    public int evaluate(Map<String, Integer> vars) {
        if (left == null && right == null) {
            return 1;
        } else {
            return left.evaluate(vars) * right.evaluate(vars);
        }
    }
}
