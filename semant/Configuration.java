package semant;

import java.util.HashMap;
import java.util.Stack;

public class Configuration {
    private HashMap<String, Integer> symTable;
    private Stack<Integer> stack;

    public Configuration() {
        symTable = new HashMap<String, Integer>();
        stack = new Stack<Integer>();
    }

    /**
     * Set the value of the given variable.
     */
    public void setVar(String var, int val) {
        symTable.put(var, val);
    }

    /**
     * Get the value of the given variable.
     */
    public int getVar(String var) {
        return symTable.get(var);
    }

    /**
     * Push the given value on the stack.
     */
    public void pushStack(int val) {
        stack.push(val);
    }

    /**
     * Pop one value from the stack.
     */
    public int popStack() {
        return stack.pop();
    }
}
