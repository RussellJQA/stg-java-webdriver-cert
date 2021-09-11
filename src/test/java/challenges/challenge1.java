package challenges;

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Challenge 1 (Java/WebDriver/testNG):
 * 1. Add a test method to go to a Google homepage (e.g., https://www.google.com, https://www.google.ca, or https://www.google.co.uk)
 * 2. Add another test method to verify that the page title is what we expected, by using the testNG Assert.
 * <p>
 * A 3rd test has been added, to borrow 2 additional challenge requirements from the Python/WebDriver Level 1 certification.
 * <p>
 * Although testGotoGoogle() can be run separately, the other 2 tests both "depend" on it being run first.
 * Such dependencies are enforced using testNG's "dependsOnMethods" attribute.
 */
public final class challenge1 extends BaseWebDriverTests {

    @DataProvider
    private static Object[][] searchData() {
        return new Object[][]{{"puppies"}};
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // GIVEN/WHEN a Google homepage is displayed
    // ----------------------------------------------------------------------

    /**
     * Displays a Google homepage. Doesn't verify any expected results.
     */
    @Test(priority = 1)
    public void testGotoGoogle() {
        initGoogleHomePage();
    }

    // ----------------------------------------------------------------------
    // THEN the page title is "Google"
    // ----------------------------------------------------------------------

    /**
     * Verifies that the resulting Web page's title is "Google". Depends on testGotoGoogle().
     *
     * @assert resulting Webpage's page title is "Google"
     */
    @Test(priority = 1, dependsOnMethods = "testGotoGoogle")
    public void testGoogleTitle() {
        assertTitleAsExpected("Google");
    }

    /**
     * Searches for the specified search key (for example, "puppies").
     * <p>
     * Although this test was not included in the requirements for challenge 1 of the STG Java/WebDriver Level 1 certification,
     * it was added for compatibility with challenge 1 of the STG Python/WebDriver Level 1 certification.
     *
     * @param searchKey a word or phrase to search for (for example, "puppies")
     * @assert resulting Webpage's title includes the search key
     */
    @Test(priority = 2, dependsOnMethods = "testGotoGoogle", dataProvider = "searchData")
    public void testSearchPuppies(String searchKey) {

        // ----------------------------------------------------------------------
        // WHEN the user searches for the specified searchKey (e.g., "puppies")
        // ----------------------------------------------------------------------

        googleHomePage.enterSearchKey(searchKey);

        // This test passed in Chrome without this wait. But it failed in Firefox without it.
        googleHomePage.waitForTitleToContain(searchKey);

        // ----------------------------------------------------------------------
        // THEN the page title of the search results page contains the specified searchKey (e.g., "puppies")
        // ----------------------------------------------------------------------

        assertTitleContains(searchKey);
    }
}