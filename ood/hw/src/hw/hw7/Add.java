package hw.hw7;

import java.util.Map;

public class Add extends ArithmeticExpression {

    public String toString(Map<String, Integer> vars) {
        if (left == null && right == null) {
            return " + ";
        } else {
            return left.toString(vars) + " + " + right.toString(vars);
        }
    }

    // I'm not checking if left == null && right != null (vice versa)
    // because the way I implemented addArgs asserts that either both
    // are present or neither are. The above holds true for add's
    // toString and Product's evaluate and toString
    public int evaluate(Map<String, Integer> vars) {
        if (left == null && right == null) {
            return 0;
        } else {
            return left.evaluate(vars) + right.evaluate(vars);
        }
    }

}
