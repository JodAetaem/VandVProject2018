package main.java;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import main.java.constantes.DefaultConst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class _MainRunner {



    public static void main(String[] args) {

        initTestInOutputFolder();
        mutationAnalysisAddToSub();
        mutationAnalysisSubToAdd();
        mutationAnalysisMulToDiv();
        mutationAnalysisDivToMul();
        mutationAnalysisInfSupInt();
        mutationAnalysisInfSupOther();
        mutationAnalysisVoidFunction();

    }

    private static void initTestInOutputFolder() {
        DefaultConst constantes = new DefaultConst();
        List<String> testsClassesName = new ArrayList<String>();
        testsClassesName.add("MathOperationTest");
        testsClassesName.add("MathSupInfTest");
        testsClassesName.add("VoidClassTest");
        try {
            new InstructionModifier().rewriter(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                    testsClassesName
                    );
        } catch (CannotCompileException e) {
            System.out.println("Error while compiling tests classes: " + e.getReason());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An Error occured while duplicating the tests: " + e.getMessage());
            e.printStackTrace();
        }
        catch (NotFoundException e){
            System.out.println("Error while duplicating tests classes.\nAre you sure the path in Constantes.class is correct?");
            System.out.println("Error message: " + e.getMessage());

        }
    }


    public static void mutationAnalysisAddToSub(){

        DefaultConst constantes = new DefaultConst();

        InstructionModifier IM = new InstructionModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - ADD TO SUB");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathOperationTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("MathOperation");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe MathOperation");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "MathOperation");
            int nbOperation = 0;
            int torollback = -1;
            for(String methodName : methodList){
                System.out.println("Modification de la methode " + methodName);
                nbOperation = IM.countOperationInClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "MathOperation", methodName, "add");

                for(int operationToChange = 0; operationToChange < nbOperation; operationToChange++){
                    System.out.println("Modification de variable position " + operationToChange);
                    torollback = IM.addToSubPreciseOperation(
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                            "MathOperation", methodName, operationToChange);

                    te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                    // Rollback the modification so it doesn't influence the next modification
                    if(torollback!=-1){
                        IM.inverseOperatorAtPosition(
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                                "MathOperation", methodName, torollback);
                    }
                }
                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }

    }



    public static void mutationAnalysisSubToAdd(){

        DefaultConst constantes = new DefaultConst();

        InstructionModifier IM = new InstructionModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - SUB TO ADD");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathOperationTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("MathOperation");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe MathOperation");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "MathOperation");
            int nbOperation = 0;
            int torollback = -1;
            for(String methodName : methodList){
                System.out.println("Modification de la methode " + methodName);
                nbOperation = IM.countOperationInClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "MathOperation", methodName, "sub");

                for(int operationToChange = 0; operationToChange < nbOperation; operationToChange++){
                    System.out.println("Modification de variable position " + operationToChange);
                    torollback = IM.subToAddPreciseOperation(
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                            "MathOperation", methodName, operationToChange);

                    te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                    // Rollback the modification so it doesn't influence the next modification
                    if(torollback!=-1){
                        IM.inverseOperatorAtPosition(
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                                "MathOperation", methodName, torollback);
                    }
                }
                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }


    }




    public static void mutationAnalysisMulToDiv(){

        DefaultConst constantes = new DefaultConst();

        InstructionModifier IM = new InstructionModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - MUL TO DIV");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathOperationTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("MathOperation");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe MathOperation");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "MathOperation");
            int nbOperation = 0;
            int torollback = -1;
            for(String methodName : methodList){
                System.out.println("Modification de la methode " + methodName);
                nbOperation = IM.countOperationInClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "MathOperation", methodName, "mul");

                for(int operationToChange = 0; operationToChange < nbOperation; operationToChange++){
                    System.out.println("Modification de variable position " + operationToChange);
                    torollback = IM.mulToDivPreciseOperation(
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                            "MathOperation", methodName, operationToChange);

                    te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                    // Rollback the modification so it doesn't influence the next modification
                    if(torollback!=-1){
                        IM.inverseOperatorAtPosition(
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                                "MathOperation", methodName, torollback);
                    }
                }
                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }
    }




    public static void mutationAnalysisDivToMul(){

        DefaultConst constantes = new DefaultConst();

        InstructionModifier IM = new InstructionModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - DIV TO MUL");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathOperationTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("MathOperation");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe MathOperation");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "MathOperation");
            int nbOperation = 0;
            int torollback = -1;
            for(String methodName : methodList){
                System.out.println("Modification de la methode " + methodName);
                nbOperation = IM.countOperationInClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "MathOperation", methodName, "div");

                for(int operationToChange = 0; operationToChange < nbOperation; operationToChange++){
                    System.out.println("Modification de variable position " + operationToChange);
                    torollback = IM.divToMulPreciseOperation(
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                            "MathOperation", methodName, operationToChange);

                    te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                    // Rollback the modification so it doesn't influence the next modification
                    if(torollback!=-1){
                        IM.inverseOperatorAtPosition(
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                                "MathOperation", methodName, torollback);
                    }
                }
                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }


    }

    public static void mutationAnalysisInfSupInt(){

        DefaultConst constantes = new DefaultConst();

        SupInfModifier IM = new SupInfModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - InfSuppInt");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathSupInfTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("MathSupInf");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe MathSupInf");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "MathSupInf");
            int nbOperation = 0;
            int torollback = -1;
            for(String methodName : methodList){
                System.out.println("Modification de la methode " + methodName);
                nbOperation = IM.countSupInfInClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "MathSupInf", methodName, "int");

                for(int operationToChange = 0; operationToChange < nbOperation; operationToChange++){
                    System.out.println("Modification de variable position " + operationToChange);
                    torollback = IM.SupInfIntPreciseOperation(
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                            "MathSupInf", methodName, operationToChange);

                    te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                    // Rollback the modification so it doesn't influence the next modification
                    if(torollback!=-1){
                        IM.inverseSupInfAtPosition(
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                                "MathSupInf", methodName, torollback);
                    }
                }
                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }


    }
    public static void mutationAnalysisInfSupOther(){

        DefaultConst constantes = new DefaultConst();

        SupInfModifier IM = new SupInfModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - InfSuppOther");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathSupInfTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("MathSupInf");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe MathSupInf");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "MathSupInf");
            int nbOperation = 0;
            int torollback = -1;
            for(String methodName : methodList){
                System.out.println("Modification de la methode " + methodName);
                nbOperation = IM.countSupInfInClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "MathSupInf", methodName, "other");

                for(int operationToChange = 0; operationToChange < nbOperation; operationToChange++){
                    System.out.println("Modification de variable position " + operationToChange);
                    torollback = IM.SupInfOtherPreciseOperation(
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                            "MathSupInf", methodName, operationToChange);

                    te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                            constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                    // Rollback the modification so it doesn't influence the next modification
                    if(torollback!=-1){
                        IM.inverseSupInfAtPosition(
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                                constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                                "MathSupInf", methodName, torollback);
                    }
                }
                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }


    }
    public static void mutationAnalysisVoidFunction(){

        DefaultConst constantes = new DefaultConst();

        VoidModifier IM = new VoidModifier();
        ClassMethods CM = new ClassMethods();
        try {
            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS - VOID ERASING");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("VoidClassTest");

            List<String> implemClasses = new ArrayList<>();
            implemClasses.add("VoidClass");


            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToTestClasses,
                    constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses, testsClasses);


            System.out.println("\nDebut des modification de la classe VoidClass");
            List<String> methodList = CM.getMethodsFromClass(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                    "VoidClass");

            for(String methodName : methodList){

                System.out.println("Modification de la methode " + methodName);

                IM.voidSuppressor(
                        constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToImplemClasses,
                        constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses,
                        "VoidClass", methodName);

                te.executeTestFromADistance(constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedTestClasses,
                        constantes.ABSOLUTE_PATH_TO_PROJECT + constantes.defaultPathToModifiedImplemClasses, testsClasses);

                // Rollback the modification so it doesn't influence the next modification




                System.out.println("Fin de la modification de "  + methodName);
            }


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////\n");



        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
            e.printStackTrace();
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
