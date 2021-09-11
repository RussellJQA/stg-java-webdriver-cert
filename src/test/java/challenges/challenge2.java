package challenges;

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Challenge 2 (Asserts):
 * 1. Go to a Copart homepage  (e.g., https://www.copart.com, https://www.copart.ca, or "https://www.copart.co.uk/")
 * 2. Search for "exotics".
 * 3. Verify "PORSCHE" is in the list of cars.
 * 4. Use the hard assertion for this challenge.
 */
public final class challenge2 extends BaseWebDriverTests {

    @DataProvider
    private static Object[][] searchData() {
        return new Object[][]{{"exotics", "PORSCHE"}};
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * @param searchKey            a word or phrase to search for (for example, "exotics")
     * @param expectedSearchResult a word or phrase expected to be in the table of search results
     * @assert the expected word or phrase is contained in the resulting table of search results
     */
    @Test(priority = 4, dataProvider = "searchData")
    public void testSearchForExotics(String searchKey, String expectedSearchResult) {

        // ----------------------------------------------------------------------
        //  GIVEN a Copart homepage is displayed
        // ----------------------------------------------------------------------

        initCopartHomePage();

        // ----------------------------------------------------------------------
        // WHEN the user searches for the specified searchKey (e.g., "exotics")
        // ----------------------------------------------------------------------

        copartHomePage.enterSearchKey(searchKey);
        copartHomePage.waitForSpinnerToComeAndGo();

        // ----------------------------------------------------------------------
        // THEN the text of the resulting search table of search results contains the expectedSearchResult (e.g., "PORSCHE")
        // ----------------------------------------------------------------------

        assertTrue(copartHomePage.getTableText().contains(expectedSearchResult));
    }
}