package main.java;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ClassMethods {

    private List<String> methods = new ArrayList<String>();

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public void getMethodsFromClass(String pathname,String className) throws NotFoundException {

        methods.clear();
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(pathname);

        CtClass functions = pool.get(className);

        for(CtMethod method : functions.getDeclaredMethods()) {
            this.methods.add(method.getName());
        }

    }
}
