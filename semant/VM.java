package semant;

import semant.amsyntax.*;

public class VM {
    private Code code;
    private Configuration conf;
    private int stepCounter = 0;

    private static boolean DEBUG;

    public VM(Code code, boolean debug) {
        this.code = code;
        DEBUG = debug;
        conf = new Configuration(); // TODO Should not be global
    }

    // public Configuration step(Configuration conf)

    /**
     * Perform one execute step of the VM, return `false`
     * if no more code can be executed.
     */
    public boolean executeStep() {
        // Break execution when no more code is available.
        if (stepCounter == code.size()) {
            if (DEBUG) System.out.println(">>> END");
            else System.out.println(conf);
            return false;
        }

        if (DEBUG) System.out.println(conf);

        Inst inst = code.get(stepCounter);
        if (DEBUG) System.out.println("> " + inst.opcode);

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
                if (a == 1) code.addAll(((Branch) inst).c1);
                else code.addAll(((Branch) inst).c2);
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
                Code c1 = ((Loop) inst).c1;
                Code c2 = ((Loop) inst).c2;
                Code c1_2 = new Code();
                Code c2_2 = new Code();
                c1_2.addAll(c2);
                c1_2.add(new Loop(c1, c2));
                c2_2.add(new Noop());
                code.addAll(c1);
                code.add(new Branch(c1_2, c2_2));
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
            case DIV:
                // TODO Add exception for division by 0
                a1 = conf.popStack();
                a2 = conf.popStack();
                conf.pushStack(a1 / a2);
                break;
            default:
                System.err.println("Invalid opcode");
                System.exit(1);
        }

        ++stepCounter;
        return true;
    }
}
