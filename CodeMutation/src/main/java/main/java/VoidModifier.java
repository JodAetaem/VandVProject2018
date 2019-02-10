package main.java;

import javassist.*;
import javassist.bytecode.CodeAttribute;

import java.io.IOException;

public class VoidModifier {


    public int voidSuppressor(String pathToSourceClasses, String pathToOutputFolder, String className, String functionName) throws NotFoundException, CannotCompileException, IOException {

        ClassPool pool = ClassPool.getDefault();
        //Choose the folder where the sources are
        pool.appendClassPath(pathToSourceClasses);

        // select the class to change
        CtClass functions = pool.get(className);

        //select the function to change
        CtMethod twice = functions.getDeclaredMethod(functionName);
        if (twice.getReturnType().equals(CtClass.voidType)){
            twice.setBody("return;");
        }



        functions.writeFile(pathToOutputFolder);
        functions.defrost();
        return 0;
    }

}
