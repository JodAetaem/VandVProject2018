package main.java;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.Opcode;

import java.io.IOException;
import java.util.List;

public class InstructionModifier {

    /***
     * Handle the change from add to sub
     * @param ListFunctionName : la liste qui contient le nom des fonctions
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    public void addToSub(List<String> ListFunctionName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();

        final String inputFolder = "./SourceCode/target/classes/";
        final String outputFolder = "./SourceModifiedCode/target/classes/";
        //Chose the folder where the sources are
        pool.appendClassPath(inputFolder);

        // select the class to change
        CtClass functions = pool.get("MathOperation");
        //select the function to change
        for (String FunctionName : ListFunctionName) {
            CtMethod twice = functions.getDeclaredMethod(FunctionName);

            CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
            byte[] code = codeAttribute.getCode();
            for (int i = 0; i < code.length; i++) {
                switch (code[i]) {
                    case Opcode.IADD:       //int
                        code[i] = Opcode.ISUB;
                        break;
                    case Opcode.FADD:       // float
                        code[i] = Opcode.FSUB;
                        break;
                    case Opcode.DADD:       // double
                        code[i] = Opcode.DSUB;
                        break;
                    case Opcode.LADD:       // long
                        code[i] = Opcode.LSUB;
                        break;
                }
            }
        }
        functions.writeFile(outputFolder);
    }
}
