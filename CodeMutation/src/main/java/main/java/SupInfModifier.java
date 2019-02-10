package main.java;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.Opcode;

import java.io.IOException;
import java.util.List;

public class SupInfModifier {

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
    public int SupPreciseOperation(String pathToSourceClasses, String pathToOutputFolder, String className, String functionName, int operationNumberWanted) throws NotFoundException, CannotCompileException, IOException {

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
                case -93:       //inferior or equal
                    if (currentPosition == operationNumberWanted) {
                        code[i] = -94;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case -94:       // inferior
                    if (currentPosition == operationNumberWanted) {
                        code[i] = -93;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case -95:       // superior or equal
                    if (currentPosition == operationNumberWanted) {
                        code[i] = -96;
                        currentPosition++;
                        positionModified = i;
                    } else {
                        currentPosition++;
                    }
                    break;
                case -96:       // superior
                    if (currentPosition == operationNumberWanted) {
                        code[i] = -95;
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

    //todo: refaire dans  instruction modifier: inverse operaterAtPosition et countOperationInClass
    //et ajouter un mutationAnalysis suoinf

    public int countSuppInfInClass(String pathToSourceClasses, String className, String functionName, String operation) throws NotFoundException {
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
            case "inforeq":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case -93:       //inferior or equal
                            nbOperation++;
                            break;
                    }
                }
                break;
            case "inf":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case -94:       //inf
                            nbOperation++;
                            break;

                    }
                }
                break;
            case "suporeq":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case -95:       //suporeq
                            nbOperation++;
                            break;

                    }
                }
                break;
            case "sup":
                for (int i = 0; i < code.length; i++) {
                    switch (code[i]) {
                        case -96:       //sup
                            nbOperation++;
                            break;
                    }
                }
        }
        functions.defrost();
        return nbOperation;
    }

    public void inverseSupInfAtPosition(String pathToSourceClasses, String pathToOutputFolder, String className, String functionName, int operationToModify) throws NotFoundException, CannotCompileException, IOException {

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
            case -93:       //InfOrEq
                code[operationToModify] = -94;
                break;
            case -94:       // Inf
                code[operationToModify] = -93;
                break;
            case -95:       // SupOrEq
                code[operationToModify] = -96;
                break;
            case -96:       // Sup
                code[operationToModify] = -95;
                break;

        }
        functions.writeFile(pathToOutputFolder);
        functions.defrost();
    }
}
