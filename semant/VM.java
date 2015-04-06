package semant;

import semant.amsyntax.Code;

public class VM {
    private Code code;
    private int stepCounter = 0;

    public VM(Code code) {
        this.code = code;
    }

    // public Configuration step(Configuration conf)

    /**
     * Perform one execute step of the VM, return `false`
     * if no more code can be executed.
     */
    public boolean executeStep() {
        // Break execution when no more code is available.
        if (stepCounter == code.size()) return false;

        ++stepCounter;
        return true;
    }
}
