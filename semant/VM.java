package semant;

import semant.amsyntax.*;

public class VM {
    private Code code;
    private Configuration conf;
    private int stepCounter = 0;

    public VM(Code code) {
        this.code = code;
        conf = new Configuration(); // TODO Should not be global
    }

    // public Configuration step(Configuration conf)

    /**
     * Perform one execute step of the VM, return `false`
     * if no more code can be executed.
     */
    public boolean executeStep() {
        // Break execution when no more code is available.
        if (stepCounter == code.size()) return false;

        Inst inst = code.get(stepCounter);
        int a, a1, a2;
        switch (inst.opcode) {
            case ADD:
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack(a1 + a2);
                break;
            case AND:
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack((a1 == 1) && (a2 == 1) ? 1 : 0);
                break;
            case BRANCH:
                a = conf.popStack();
                // TODO Correct?
                if (a == 1) code = ((Branch) inst).c1;
                else code = ((Branch) inst).c2;
                break;
            case EQ:
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack(a1 == a2 ? 1 : 0);
                break;
            case FALSE:
                conf.pushStack(0); // False
                break;
            case FETCH:
                a = conf.getVar(((Fetch) inst).x);
                conf.pushStack(a);
                break;
            case LE:
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack(a1 <= a2 ? 1 : 0);
                break;
            case LOOP:
                // TODO
                break;
            case MULT:
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack(a1 * a2);
                break;
            case NEG:
                a = conf.popStack();
                conf.pushStack((a == 1) ? 0 : 1);
                break;
            case NOOP:
                break;
            case PUSH:
                conf.pushStack(((Push) inst).getValue());
                break;
            case STORE:
                a = conf.popStack();
                conf.setVar(((Store) inst).x, a);
                break;
            case SUB:
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack(a1 - a2);
                break;
            case TRUE:
                conf.pushStack(1); // True
                break;
            default:
                System.err.println("Invalid opcode");
                System.exit(1);
        }

        ++stepCounter;
        return true;
    }
}
