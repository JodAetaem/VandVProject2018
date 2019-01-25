package main.java;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.Opcode;

public class InstructionModifier {

    public static void main(String... args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        final String folder = "./manipulation-target/target/classes/";
        final String folder2 = "./zboui";
        pool.appendClassPath(folder);

        CtClass functions = pool.get("m2.vv.tutorial.Functions");

        CtMethod twice = functions.getDeclaredMethod("twiceplusone");

        CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
        byte[] code = codeAttribute.getCode();
        for(int i = 0; i< code.length; i++) {
            if(code[i] == Opcode.DMUL) {
                // This can be done since there is no change in the stack
                // or local variables.
                // For more complex transformations check CodeConverter and ExprEditor
                code[i] = Opcode.DADD;
            }
        }

        functions.writeFile(folder2);
    }
}
