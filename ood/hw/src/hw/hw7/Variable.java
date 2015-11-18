package hw.hw7;

import java.util.Map;

public class Variable extends ArithmeticExpression {

    private String var;

    public Variable (String var) {
        this.var = var;
    }

    public int evaluate(Map<String, Integer> vars) {
        if (vars.containsKey(var)) {
            return vars.get(var);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public String toString(Map<String, Integer> vars) {
        if (vars.containsKey(var)) {
            return Integer.toString(vars.get(var));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public String getVar() {
        return var;
    }

}
