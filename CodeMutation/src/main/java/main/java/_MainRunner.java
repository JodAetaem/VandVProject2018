package main.java;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import main.java.constantes.DefaultConst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class _MainRunner {

    public static void main(String[] args){

        //mutationAnalysisAddToSub();
        mutationAnalysisSubToAdd();

    }



    public static void mutationAnalysisAddToSub(){

        DefaultConst constantes = new DefaultConst();

        InstructionModifier IM = new InstructionModifier();
        ClassMethods CM = new ClassMethods();
        try {

            // Modification de la class source
            List<String> AllFunctionsToModify = new ArrayList<String>();
            AllFunctionsToModify.add("add");
            AllFunctionsToModify.add("substract");
            AllFunctionsToModify.add("addDouble");

            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS");
            List<String> testsClasses = new ArrayList<String>();        // TODO recuperer toutes les noms de classes
            testsClasses.add("MathOperationTest");

            List<String> implemClasses = new ArrayList<>();         // TODO recuperer le nom des classes d'implem
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
                nbOperation = IM.countOperationInClass("./" + constantes.defaultPathToModifiedImplemClasses,
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


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////");



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

            // Modification de la class source
            List<String> AllFunctionsToModify = new ArrayList<String>();
            AllFunctionsToModify.add("add");
            AllFunctionsToModify.add("substract");
            AllFunctionsToModify.add("addDouble");

            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS");
            List<String> testsClasses = new ArrayList<String>();        // TODO recuperer toutes les noms de classes
            testsClasses.add("MathOperationTest");

            List<String> implemClasses = new ArrayList<>();         // TODO recuperer le nom des classes d'implem
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
                nbOperation = IM.countOperationInClass("./" + constantes.defaultPathToModifiedImplemClasses,
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


            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////");



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
