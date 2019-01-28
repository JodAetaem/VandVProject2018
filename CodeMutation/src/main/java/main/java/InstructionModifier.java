package main.java;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.Opcode;

public class InstructionModifier {

    public static void main(String... args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        final String folder = "./SourceCode/target/classes/";
        final String folder2 = "./SourceModifiedCode/target/classes/";
        pool.appendClassPath(folder);

        CtClass functions = pool.get("MathOperation");

        CtMethod twice = functions.getDeclaredMethod("add");

        CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
        byte[] code = codeAttribute.getCode();
        for(int i = 0; i< code.length; i++) {
            System.out.println("Cherche " + Opcode.DADD + " trouvé :" + code[i]);
            if(code[i] == Opcode.IADD) {
                // This can be done since there is no change in the stack
                // or local variables.
                // For more complex transformations check CodeConverter and ExprEditor
                code[i] = Opcode.DSUB;
            }
        }

        functions.writeFile(folder2);
    }
}
