// file: Evaluator.java
// author: Rootul Patel
// date: January 30, 2014
//
// The Evaluator
//

import java.util.*;

public class Evaluator {

    //Do the math according to the operation
    private static double evaluate(double x, String op, double y) {
        if(op.equals("+")) {return x + y;}
        else if(op.equals("-")) {return x - y;}
        else if(op.equals("*")) {return x * y;}
        else if(op.equals("/")) {return x / y;}
        else {return Math.pow(x, y);}
    }
    
    //Helper function to discern numbers from operators
    private static boolean isOperator(String token) {
        return (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"));
    }
    
    //Helper functions for parentheses
    private static boolean isLeftParen(String token){
        return (token.equals("("));
    }
    private static boolean isRightParen(String token){
        return (token.equals(")"));
    }
    
    //Helper functions for PEMDAS and Precedence
    private static int pemdas(String op) {
        if(op.equals("+")) {return 1;}
        else if(op.equals("-")) {return 1;}
        else if(op.equals("*")) {return 2;}
        else if(op.equals("/")) {return 2;}
        else if(op.equals("^")) {return 3;}
        else if(op.equals("(")) {return 0;}
        else {return 0;}
    }
    private static boolean precedence(String op1, String op2) {
        return pemdas(op1) >= pemdas(op2);
    }
    
    //The MAIN!
    public static void main(String[] args) {
        Stack<Double> valStack = new ResizingArrayStack<Double>();
        Stack<String> opStack = new ResizingArrayStack<String>();
        
        while(!StdIn.isEmpty()) {
            String token = StdIn.readString();
            
            //If Operator
            if(isOperator(token)) {
                String current = token;
                while(!opStack.isEmpty() && precedence(opStack.peek(), current)) {
                    //Pop last two numbers and last operator and do math
                    String op = opStack.pop();
                    double x = valStack.pop();
                    double y = valStack.pop();
                    double result = evaluate(x, op, y);
                    //Push result to valStack
                    valStack.push(result);
                }
                //Push operator to opStack
                opStack.push(token);
            
            //If ")"
            } else if(token.equals(")")) {
                while(!opStack.peek().equals("(")) {
                    //Pop last two numbers and last operator and do math
                    String op = opStack.pop();
                    double y = valStack.pop();
                    double x = valStack.pop();
                    double result = evaluate(x, op, y);
                    //Push result to valStack
                    valStack.push(result);
                }
                //Pop last operator from opStack and delete
                opStack.pop();
                
            //If "("    
            } else if(token.equals("(")) {
                opStack.push(token);
                
            } else valStack.push(Double.parseDouble(token));
        }
        StdOut.println(valStack.pop());
    }
}
    