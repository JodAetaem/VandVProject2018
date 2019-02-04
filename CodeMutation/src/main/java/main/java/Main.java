package main.java;

import javassist.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        InstructionModifier IM = new InstructionModifier();
        try {

            String pathTestClasses = "/home/istic-larzilliere/Documents/VV/Project2019/SourceCode/target/test-classes/";
            String pathClasses = "/home/istic-larzilliere/Documents/VV/Project2019/SourceCode/target/classes/";

            // Modification de la class source
            List<String> AllFunctionoToModify = new ArrayList<String>();
            AllFunctionoToModify.add("add");
            AllFunctionoToModify.add("addDouble");

            // Execution des tests
            TestExecutor te = new TestExecutor();
            te.executeTestFromADistance(pathTestClasses,pathClasses);

            IM.addToSub(AllFunctionoToModify);


            System.out.println("Fin de l'execution du programme");

        } catch (NotFoundException e) {
            System.out.println("Imposible de trouver le fichier source: " + e.getMessage());
        } catch (CannotCompileException e) {
            System.out.println("Erreur lors de la compilation: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // ERREUR EXECUTION TEST A GERER
            System.out.println("Erreur lors du chargement de la classe de test: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
