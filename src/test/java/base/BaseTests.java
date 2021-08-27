// This class is a base class for other tests

package base;

import org.testng.annotations.AfterSuite;

public class BaseTests {

    @AfterSuite
    public void stopSuite() {
        System.out.println("\n*** All done!!!");
    }
}