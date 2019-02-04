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
    public void addToSub(String pathToSourceClasses, String pathToOutputFolder, List<String> ListFunctionName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();

        //final String inputFolder = "./SourceCode/target/classes/";
        //final String outputFolder = "./SourceModifiedCode/target/classes/";
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

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
        functions.writeFile(pathToOutputFolder);
        functions.defrost();
    }

    /***
     * Handle the change from sub to add
     * @param ListFunctionName : la liste qui contient le nom des fonctions
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    public void subToAdd(String pathToSourceClasses, String pathToOutputFolder, List<String> ListFunctionName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
//
//        final String inputFolder = "./SourceCode/target/classes/";
//        final String outputFolder = "./SourceModifiedCode/target/classes/";
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

        // select the class to change
        CtClass functions = pool.get("MathOperation");
        //select the function to change
        for (String FunctionName : ListFunctionName) {
            CtMethod twice = functions.getDeclaredMethod(FunctionName);

            CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
            byte[] code = codeAttribute.getCode();
            for (int i = 0; i < code.length; i++) {
                switch (code[i]) {
                    case Opcode.ISUB:       //int
                        code[i] = Opcode.IADD;
                        break;
                    case Opcode.FSUB:       // float
                        code[i] = Opcode.FADD;
                        break;
                    case Opcode.DSUB:       // double
                        code[i] = Opcode.DADD;
                        break;
                    case Opcode.LSUB:       // long
                        code[i] = Opcode.LADD;
                        break;
                }
            }
        }
        functions.writeFile(pathToOutputFolder);
        functions.defrost();
    }

    /**
     * Rewrite the classes "ClassesNames" found in the "inputfolder" to the "outputfolder"
     * @param inputFolder
     * @param outputFolder
     * @param classesNames
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    public void rewriter(String inputFolder, String outputFolder, List<String> classesNames ) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(inputFolder);

        // select the class to change
        for(String className : classesNames){
            CtClass functions = pool.get(className);
            functions.writeFile(outputFolder);
        }
    }
}
