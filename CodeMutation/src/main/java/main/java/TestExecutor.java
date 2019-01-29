package main.java;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestExecutor {

    public void executeTestFromADistance() throws ClassNotFoundException, MalformedURLException {

//        URL classUrl;
//        classUrl = new URL("file:///home/istic-larzilliere/Documents/VV/Project2019/SourceCode/target/test-classes/");
//        URL[] classUrls = { classUrl };
//        URLClassLoader ucl = new URLClassLoader(classUrls);
//        Class c = ucl.loadClass("MathOperationTest");
//
//        Class<?>[] classes = {c};
//        Request request = Request.classes(new Computer(), classes);
//        Runner runner = request.getRunner();
//        Result result = new Result();
//        RunNotifier notifier = new RunNotifier();
//       // RequestStopListener listener = new RequestStopListener(notifier);
//
//
//        try {
//            runner.run(notifier);
//        }
//        catch(StoppedByUserException exc) {
//            System.out.println("Process stopped");
//        }
//        System.out.println("Did all test passed? " + result.wasSuccessful());
//        System.out.print("Tests executed: " + result.getRunCount());

        JUnitCore core = new JUnitCore();

        core.addListener( new RunListener() {

            @Override
            public void testRunStarted(Description description) throws Exception {
                System.out.println("RUN STARTED");
            }

            @Override
            public void testStarted(Description description) throws Exception {
                System.out.println("TEST STARTED: " +  description.getDisplayName());
            }

            @Override
            public void testFailure(Failure failure) throws Exception {
                System.out.println("FAILURE: " + failure.getMessage());
            }

            @Override
            public void testIgnored(Description description) throws Exception {
                System.out.println("TEST IGNORED: " + description.getDisplayName());
            }

            @Override
            public void testFinished(Description description) throws Exception {
                System.out.println("TEST FINISHED: " + description.getDisplayName());
            }


            @Override
            public void testRunFinished(Result result) throws Exception {
                System.out.println("RUN FINISHED");
                System.out.println(String.format("| IGNORED: %d", result.getIgnoreCount()));
                System.out.println(String.format("| FAILURES: %d", result.getFailureCount()));
                System.out.println(String.format("| RUN: %d", result.getRunCount()));
            }

        } );

        URL classUrl;
        classUrl = new URL("file:///home/istic-larzilliere/Documents/VV/Project2019/SourceCode/target/test-classes/");
        URL[] classUrls = { classUrl };
        URLClassLoader ucl = new URLClassLoader(classUrls);
        Class c = ucl.loadClass("MathOperationTest");


        core.run(c.getClass());

    }


}
