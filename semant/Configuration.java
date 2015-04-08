package semant;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
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

    /**
     * Return a string representation of this configuration.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("========== Symbol Table ==========\n");
        for (Map.Entry<String, Integer> entry : symTable.entrySet()) {
            String var = entry.getKey();
            int val = entry.getValue();
            sb.append(var + ": " + val + "\n");
        }

        sb.append("\n");

        sb.append("======== Stack (Top-Down) ========\n");
        // Java's stack iterator is reversed
        for (ListIterator<Integer> it = stack.listIterator(stack.size());
                it.hasPrevious();) {
            sb.append(it.previous() + "\n");
        }

        return sb.toString();
    }
}
