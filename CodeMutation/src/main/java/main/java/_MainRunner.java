package main.java;

import javassist.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class _MainRunner {

    public static void main(String[] args){
        String ABSOLUTE_PATH_TO_PROJECT = "/home/istic-larzilliere/Documents/VV/Project2019/";

        String defaultPathToTestClasses = "SourceCode/target/test-classes/";
        String defaultPathToImplemClasses = "SourceCode/target/classes/";

        String defaultPathToModifiedTestClasses = "SourceModifiedCode/target/test-classes/";
        String defaultPathToModifiedImplemClasses = "SourceModifiedCode/target/classes/";

        InstructionModifier IM = new InstructionModifier();
        try {

            // Modification de la class source
            List<String> AllFunctionsToModify = new ArrayList<String>();
            AllFunctionsToModify.add("add");
            AllFunctionsToModify.add("substract");
            //AllFunctionoToModify.add("addDouble");

            // Execution des tests
            System.out.println("TESTS AVANT LES MODIFICATIONS");
            List<String> testsClasses = new ArrayList<String>();
            testsClasses.add("MathOperationTest");
            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(ABSOLUTE_PATH_TO_PROJECT + defaultPathToTestClasses,
                    ABSOLUTE_PATH_TO_PROJECT + defaultPathToImplemClasses, testsClasses);

            //Modification de classes
            System.out.println("\nModifications des + en -");
            IM.addToSub("./"+defaultPathToImplemClasses,"./"+defaultPathToModifiedImplemClasses,AllFunctionsToModify);

            //Réécritures des tests
            IM.rewriter("./" + defaultPathToTestClasses,
                    "./" + defaultPathToModifiedTestClasses,
                    testsClasses);

            System.out.println("TESTS APRES LES MODIFICATIONS");
            te.executeTestFromADistance(ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedTestClasses,
                    ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedImplemClasses, testsClasses);


            //Modification de classes
            System.out.println("\nModifications des - en +");
            IM.subToAdd("./"+ defaultPathToImplemClasses,"./"+defaultPathToModifiedImplemClasses,AllFunctionsToModify);

            //Réécritures des tests
            IM.rewriter("./" + defaultPathToTestClasses,
                    "./" + defaultPathToModifiedTestClasses,
                    testsClasses);

            System.out.println("TESTS APRES LES MODIFICATIONS");
            te.executeTestFromADistance(ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedTestClasses,
                    ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedImplemClasses, testsClasses);

            System.out.println("\n//////////////////   Fin de l'execution du programme   //////////////////");

            System.out.println("\nTEST A DELETE EN DESSOUS DE CE POINT\n");


            System.out.println(IM.countOperationInClass("./" + defaultPathToModifiedImplemClasses,
                    "MathOperation", "add3TimesAndSub1", "sub"));



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
