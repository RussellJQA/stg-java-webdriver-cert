package challenges;

import base.BaseTests;
import org.testng.annotations.Test;

public class challenge1 extends BaseTests {

    @Test
    public void testGoogleTitle() {
        goToGoogle();
        assertTitleAsExpected("Google");

        // TODO: The Python WebDriver certification also has:
        //      1. Search for “puppies”
        //      2. Assert that the results page that loads has “puppies” in its title
        // So, for comparison between Java and Python, implement that as well.
    }
}