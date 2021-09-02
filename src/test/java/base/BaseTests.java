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
     *
     */
    @AfterSuite
    public void stopSuite() {
        System.out.println("\n*** All done!!!");
    }
}