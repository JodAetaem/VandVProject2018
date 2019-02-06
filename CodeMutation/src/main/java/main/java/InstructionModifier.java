package main.java;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.Opcode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InstructionModifier {

    /**
     * Handle the change from add to sub
     *
     * @param pathToSourceClasses Chemin vers la classe a modifier
     * @param pathToOutputFolder  Chemin ou sera écrit la modification (finir par /target/classes/)
     * @param ListFunctionName    La ou les methodes a modifiers
     * @throws NotFoundException      impossible de trouver la classe
     * @throws CannotCompileException ne compile pas
     * @throws IOException            exception
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

    /**
     * Handle the change from sub to add
     *
     * @param pathToSourceClasses Chemin vers la classe a modifier
     * @param pathToOutputFolder  Chemin ou sera écrit la modification (finir par /target/classes/)
     * @param ListFunctionName    La ou les methodes a modifiers
     * @throws NotFoundException      impossible de trouver la classe
     * @throws CannotCompileException ne compile pas
     * @throws IOException            exception
     */
    public void subToAdd(String pathToSourceClasses, String pathToOutputFolder, List<String> ListFunctionName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
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
     * Change add to sub
     *
     * @param pathToSourceClasses   chemin des classes sources
     * @param pathToOutputFolder    chemin de sortie
     * @param className             nom de la classe
     * @param functionName          nom de la fonction
     * @param operationNumberWanted la position de l'opération a changer
     * @throws NotFoundException      e
     * @throws CannotCompileException e
     * @throws IOException            e
     */
    public int addToSubPreciseOperation(String pathToSourceClasses, String pathToOutputFolder, String className, String functionName, int operationNumberWanted) throws NotFoundException, CannotCompileException, IOException {

        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

        // select the class to change
        CtClass functions = pool.get(className);

        //select the function to change
        CtMethod twice = functions.getDeclaredMethod(functionName);
        int currentPosition = 0;
        CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
        byte[] code = codeAttribute.getCode();

        int positionModified = -1;

        for (int i = 0; i < code.length; i++) {
            switch (code[i]) {
                case Opcode.IADD:       //int
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.ISUB;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case Opcode.FADD:       // float
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.FSUB;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case Opcode.DADD:       // double
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.DSUB;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case Opcode.LADD:       // long
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.LSUB;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
            }
        }
        functions.writeFile(pathToOutputFolder);
        pool.clearImportedPackages();
        functions.defrost();
        return positionModified;
    }


    public int subToAddPreciseOperation(String pathToSourceClasses, String pathToOutputFolder, String className, String functionName, int operationNumberWanted) throws NotFoundException, CannotCompileException, IOException {

        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

        // select the class to change
        CtClass functions = pool.get(className);

        //select the function to change
        CtMethod twice = functions.getDeclaredMethod(functionName);
        int currentPosition = 0;
        CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
        byte[] code = codeAttribute.getCode();

        int positionModified = -1;

        for (int i = 0; i < code.length; i++) {
            switch (code[i]) {
                case Opcode.ISUB:       //int
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.IADD;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                        System.out.println("char trouvé mais pas la position voulu");
                    }
                    break;
                case Opcode.FSUB:       // float
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.FADD;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case Opcode.DSUB:       // double
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.DADD;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case Opcode.LSUB:       // long
                    if (currentPosition == operationNumberWanted) {
                        code[i] = Opcode.LADD;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                //default: System.out.println("Pas de le char voulu");
            }
        }
        functions.writeFile(pathToOutputFolder);
        functions.defrost();
        return positionModified;
    }


    /**
     * Rewrite the classes "ClassesNames" found in the "inputfolder" to the "outputfolder"
     *
     * @param inputFolder  chemin de la source
     * @param outputFolder chemin d'écriture
     * @param classesNames les classes a recopier
     * @throws NotFoundException      impossible de trouver la classe source
     * @throws CannotCompileException ne compite pas
     * @throws IOException            une exception
     */
    public void rewriter(String inputFolder, String outputFolder, List<String> classesNames) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(inputFolder);

        // select the class to change
        for (String className : classesNames) {
            CtClass functions = pool.get(className);
            functions.writeFile(outputFolder);
            functions.defrost();
        }

    }

    /**
     * Count the amount of time an operation is present in the said function
     *
     * @param pathToSourceClasses chemin vers la classe source
     * @param className           nom de la classe
     * @param functionName        nom de la methode (dans la classe)
     * @param operation           operation voulu (add, sub, div, mul)
     * @return nombre d'occurence
     * @throws NotFoundException impossible de trouver la classe
     */
    public int countOperationInClass(String pathToSourceClasses, String className, String functionName, String operation) throws NotFoundException {
        int nbOperation = 0;


        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

        // select the class to change
        CtClass functions = pool.get(className);
        //select the function to change
        CtMethod twice = functions.getDeclaredMethod(functionName);

        CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
        byte[] code = codeAttribute.getCode();

        switch (operation) {
            case "add":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case Opcode.IADD:       //int
                            nbOperation++;
                            break;
                        case Opcode.FADD:       // float
                            nbOperation++;
                            break;
                        case Opcode.DADD:       // double
                            nbOperation++;
                            break;
                        case Opcode.LADD:       // long
                            nbOperation++;
                            break;
                    }
                }
                break;
            case "sub":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case Opcode.ISUB:       //int
                            nbOperation++;
                            break;
                        case Opcode.FSUB:       // float
                            nbOperation++;
                            break;
                        case Opcode.DSUB:       // double
                            nbOperation++;
                            break;
                        case Opcode.LSUB:       // long
                            nbOperation++;
                            break;
                    }
                }
                break;
            case "mul":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case Opcode.IMUL:       //int
                            nbOperation++;
                            break;
                        case Opcode.FMUL:       // float
                            nbOperation++;
                            break;
                        case Opcode.DMUL:       // double
                            nbOperation++;
                            break;
                        case Opcode.LMUL:       // long
                            nbOperation++;
                            break;
                    }
                }
                break;
            case "div":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case Opcode.IDIV:       //int
                            nbOperation++;
                            break;
                        case Opcode.FDIV:       // float
                            nbOperation++;
                            break;
                        case Opcode.DDIV:       // double
                            nbOperation++;
                            break;
                        case Opcode.LDIV:       // long
                            nbOperation++;
                            break;
                    }
                }
            default:
                System.out.println("Aucune opération " + operation + " dans la fonction " + functionName +
                        "de la classe " + className);
                return 0;
        }
        functions.defrost();
        return nbOperation;
    }

    public void fileDeleter(String pathToFile) {
        File toDelete = new File(pathToFile);
        if (toDelete.delete()) {
            System.out.println(pathToFile + " deleted");
        } else {
            System.out.println("ERROR DELETING");
        }
    }


    /**
     * To execute after a modification to rollit back.
     * use the value return by addToSub-like function
     * @param pathToSourceClasses path to source classes (like previous method, probably useless)
     * @param pathToOutputFolder  path to output the modification
     * @param className             the class to modify
     * @param functionName         the function to modify
     * @param operationToModify     the precise spot of the modification to roll back
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    public void inverseOperatorAtPosition(String pathToSourceClasses, String pathToOutputFolder, String className, String functionName, int operationToModify) throws NotFoundException, CannotCompileException, IOException {

        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

        // select the class to change
        CtClass functions = pool.get(className);

        //select the function to change
        CtMethod twice = functions.getDeclaredMethod(functionName);
        int currentPosition = 0;
        CodeAttribute codeAttribute = twice.getMethodInfo().getCodeAttribute();
        byte[] code = codeAttribute.getCode();

        switch (code[operationToModify]) {
            case Opcode.IADD:       //int
                code[operationToModify] = Opcode.ISUB;
                break;
            case Opcode.FADD:       // float
                code[operationToModify] = Opcode.FSUB;
                break;
            case Opcode.DADD:       // double
                code[operationToModify] = Opcode.DSUB;
                break;
            case Opcode.LADD:       // long
                code[operationToModify] = Opcode.LSUB;
                break;
            case Opcode.ISUB:       //int
                code[operationToModify] = Opcode.IADD;
                break;
            case Opcode.FSUB:       // float
                code[operationToModify] = Opcode.FADD;
                break;
            case Opcode.DSUB:       // double
                code[operationToModify] = Opcode.DADD;
                break;
            case Opcode.LSUB:       // long
                code[operationToModify] = Opcode.LADD;
                break;
            case Opcode.IMUL:       //int
                code[operationToModify] = Opcode.IDIV;
                break;
            case Opcode.FMUL:       // float
                code[operationToModify] = Opcode.FDIV;
                break;
            case Opcode.DMUL:       // double
                code[operationToModify] = Opcode.DDIV;
                break;
            case Opcode.LMUL:       // long
                code[operationToModify] = Opcode.LDIV;
                break;
            case Opcode.IDIV:       //int
                code[operationToModify] = Opcode.IMUL;
                break;
            case Opcode.FDIV:       // float
                code[operationToModify] = Opcode.FMUL;
                break;
            case Opcode.DDIV:       // double
                code[operationToModify] = Opcode.DMUL;
                break;
            case Opcode.LDIV:       // long
                code[operationToModify] = Opcode.LMUL;
                break;
        }
        functions.writeFile(pathToOutputFolder);
        functions.defrost();
    }
}


