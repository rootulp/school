package hw.hw7;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class HW7Test {

    public static void main(String[] args) {

        Add add1 = new Add();
        Product mult1 = new Product();
        Add add2 = new Add();

        Map<String, Integer> vars1 = new HashMap<>();
        vars1.put("x", 6);
        vars1.put("y", 4);

        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Number two = new Number(10);
        Number three = new Number(5);

        mult1.addArg(x, y);
        add1.addArg(two, three);
        add2.addArg(mult1, add1);

        System.out.println("Test Case 1:");
        System.out.println(add2.toString(vars1));
        System.out.println(add2.evaluate(vars1));
        System.out.println();


        System.out.println("External Iteration:");
        externalIterationExample(add2, vars1);
        System.out.println();

        System.out.println("Internal Iteration:");
        ArithmeticExpression.traverse(add2);
        System.out.println();

        Add add3 = new Add();
        Map<String, Integer> vars2 = new HashMap<>();
        System.out.println("Test Case 2: Add with no children");
        System.out.println(add3.toString(vars2));
        System.out.println(add3.evaluate(vars2));
        System.out.println();

        Product mult2 = new Product();
        Map<String, Integer> vars3 = new HashMap<>();
        System.out.println("Test Case 3: Multiply with no children");
        System.out.println(mult2.toString(vars3));
        System.out.println(mult2.evaluate(vars3));

    }

    public static void externalIterationExample(ArithmeticExpression root, Map<String, Integer> vars) {

        int max = 0;
        int numberOfIntegers = 0;
        int numberOfVariables = 0;
        Iterator<ArithmeticExpression> iter = new ArithmeticExpressionIterator(root);
        while (iter.hasNext()) {
            ArithmeticExpression ae = iter.next();

            if ((ae instanceof Number || ae instanceof Variable) && ae.evaluate(vars) > max) {
                max = ae.evaluate(vars);
            }

            if (ae instanceof Number) {
                numberOfIntegers += 1;
            } else if (ae instanceof Variable) {
                numberOfVariables += 1;
            }

        }

        System.out.println("Max: " + max);
        System.out.println("Number of Integers: " + numberOfIntegers);
        System.out.println("Number of Variables: " + numberOfVariables);
    }
}
