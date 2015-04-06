package semant;

import semant.amsyntax.Code;
import semant.amsyntax.Inst;
import semant.whilesyntax.Stm;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean DEBUG = false;

    public static void main(String[] args) throws Exception {
        if (args.length > 1 && args[1].equals("-d"))
            DEBUG = true;

        // Generate While AST
        Stm s = WhileParser.parse(args[0]);

        // Print While AST
        s.accept(new PrettyPrinter());
        System.out.println();

        // Compile s into AM Code AST
        Code am = s.accept(new CompileVisitor());

        for (Inst inst : am) System.out.println(inst);

        // Init VM
        VM vm = new VM(am);

        // Execute resulting AM Code using a step-function
        while (true)
            new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
