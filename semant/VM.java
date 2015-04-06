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

        switch (code.get(stepCounter).opcode) {
            case ADD:
                break;
            case AND:
                break;
            case BRANCH:
                break;
            case EQ:
                break;
            case FALSE:
                break;
            case FETCH:
                break;
            case LE:
                break;
            case LOOP:
                break;
            case MULT:
                break;
            case NEG:
                break;
            case NOOP:
                break;
            case PUSH:
                break;
            case STORE:
                break;
            case SUB:
                break;
            case TRUE:
                break;
            default:
                System.err.println("Invalid opcode");
                System.exit(1);
        }

        ++stepCounter;
        return true;
    }
}
