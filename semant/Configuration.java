package semant;

import java.util.HashMap;

public class Configuration {
    private HashMap<String, Integer> symTable;

    public Configuration() {
        symTable = new HashMap<String, Integer>();
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
}
