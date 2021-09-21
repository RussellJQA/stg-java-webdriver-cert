package base;

import org.testng.annotations.AfterSuite;

/**
 * Base class for other tests
 */
public class BaseTests {

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * This function will be run after all tests in the current suite (as specified by a <suite> tag in a test*.xml file) have run.
     */
    @AfterSuite
    public void stopSuite() {
        System.out.println("\n*** All done!!!");
    }
}