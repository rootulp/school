package hw.hw7;

import java.util.Stack;
import java.util.Iterator;

public class ArithmeticExpressionIterator implements Iterator {

    private Stack<ArithmeticExpression> stack;

    public ArithmeticExpressionIterator(ArithmeticExpression root) {
        stack = new Stack<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public ArithmeticExpression next() {
        ArithmeticExpression node = stack.pop();
        ArithmeticExpression result = node;

        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        return result;
    }
}
