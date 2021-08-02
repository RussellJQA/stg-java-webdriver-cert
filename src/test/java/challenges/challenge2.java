package challenges;

/*
Challenge 2 (Asserts):
1. Go to the https://www.copart.com main page.
2. Search for "exotics".
3. Verify "PORSCHE" is in the list of cars.
4. Use the hard assertion for this challenge.
*/

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class challenge2 extends BaseTests {

    private final static String searchKey = "exotics";
    private final static String expectedSearchResult = "PORSCHE";

    @Test(priority = 4)
    public void testSearchForExotics() {

        //  GIVEN the Copart homepage is displayed
        initCopartHomePage();

        // WHEN the user searches for the specified search phrase
        copartHomePage.enterSearchKey(searchKey);
        copartHomePage.waitForSpinnerToComeAndGo();

        // THEN the text of the resulting table (of search results) contains the specified search result
        assertTrue(copartHomePage.getTableText().contains(expectedSearchResult));
    }
}