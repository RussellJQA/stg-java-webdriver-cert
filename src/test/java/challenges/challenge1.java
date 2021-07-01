package challenges;

import base.BaseTests;
import org.testng.annotations.Test;

public class challenge1 extends BaseTests {

    private final static String searchKey = "puppies";

    @Test
    public void testGoogleTitle() {
        initGoogleHomePage();
        assertTitleAsExpected("Google");

        // The Python WebDriver certification also includes 2 additional requirements:
        // • Search for “puppies”
        // • Assert that the results page that loads has “puppies” in its title
        // So, for comparison between Java and Python, that has been implemented below.
        googleHomePage.enterSearchKey(searchKey);
        assertTitleContains(searchKey);
    }
}