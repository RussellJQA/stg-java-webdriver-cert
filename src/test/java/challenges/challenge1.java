package challenges;

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Challenge 1 (Java/WebDriver/testNG):
 * 1. Add a test method to go to the https://www.google.com main page.
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
    // GIVEN/WHEN the Google search page is displayed
    // ----------------------------------------------------------------------

    /**
     * This test simply displays the Google search page, without checking for any specific expected results.
     */
    @Test(priority = 1)
    public void testGotoGoogle() {
        initGoogleHomePage();
    }

    // ----------------------------------------------------------------------
    // THEN the page title is "Google"
    // ----------------------------------------------------------------------

    /**
     * Then the following, dependent test verifies that the resulting Web page's page title is "Google"
     *
     * @assert Resulting Webpage's page title is "Google"
     */
    @Test(priority = 1, dependsOnMethods = "testGotoGoogle")
    public void testGoogleTitle() {
        assertTitleAsExpected("Google");
    }

    /**
     * The STG Python/WebDriver Level 1 (Automation Associate) certification includes 2 additional requirements for challenge 1:
     * • Search for the specified search key (e.g., "puppies")
     * • Assert that the results page that loads has the specified search key in its title
     * So, for comparison between Java and Python, that has been implemented as an additional dependent test below.
     *
     * @param searchKey Search key (e.g., "puppies")
     * @assert Resulting Webpage's page title includes the Search key
     */
    @Test(priority = 2, dependsOnMethods = "testGotoGoogle", dataProvider = "searchData")
    public void testSearchPuppies(String searchKey) {

        // ----------------------------------------------------------------------
        // WHEN the user searches for the specified search phrase
        // ----------------------------------------------------------------------

        googleHomePage.enterSearchKey(searchKey);

        // This test passed in Chrome without this wait. But it failed in Firefox without it.
        googleHomePage.waitForTitleToContain(searchKey);

        // ----------------------------------------------------------------------
        // THEN the page title of the search results contains the specified search phrase
        // ----------------------------------------------------------------------

        assertTitleContains(searchKey);
    }
}