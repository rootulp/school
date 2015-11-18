package hw.hw7;

import java.util.Map;
import java.util.Iterator;

public abstract class ArithmeticExpression {

    protected ArithmeticExpression left;
    protected ArithmeticExpression right;

    public void addArg(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    public abstract int evaluate(Map<String, Integer> vars);
    public abstract String toString(Map<String, Integer> vars);

    public static void traverse(ArithmeticExpression root) {
        Iterator<ArithmeticExpression> iter = new ArithmeticExpressionIterator(root);
        while (iter.hasNext()) {
            ArithmeticExpression ae = iter.next();

            if (ae instanceof Variable) {
                Variable var = (Variable) ae;
                System.out.println(var.getVar());
            }

        }
    }
}
