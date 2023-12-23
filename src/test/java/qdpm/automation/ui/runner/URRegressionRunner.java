package qdpm.automation.ui.runner;
import org.testng.TestNG;
import qdpm.automation.ui.steps.TestMain;

import java.util.ArrayList;
import java.util.List;

public class URRegressionRunner {
    public static void main(String[] args) {
        // Create a TestNG object
        TestNG testNG = new TestNG();

        // Create a list to store your test classes
        List<Class<?>> classes = new ArrayList<>();

        // Add your test class to the list
        classes.add(TestMain.class);

        // Convert the list to an array of Class objects
        Class<?>[] classesArray = classes.toArray(new Class<?>[0]);

        // Set the array of test classes to the TestNG object
        testNG.setTestClasses(classesArray);

        // Run the tests
        testNG.run();
    }
}

