package challenges;

/*
Challenge 2 (Asserts):
1. Go to the https://www.copart.com main page.
2. Search for "exotics".
3. Verify "PORSCHE" is in the list of cars.
4. Use the hard assertion for this challenge.
*/

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public final class challenge2 extends BaseWebDriverTests {

    @DataProvider
    private static Object[][] searchData() {
        return new Object[][]{{"exotics", "PORSCHE"}};
    }

    @Test(priority = 4, dataProvider = "searchData")
    public void testSearchForExotics(String searchKey, String expectedSearchResult) {

        //  GIVEN the Copart homepage is displayed
        initCopartHomePage();

        // WHEN the user searches for the specified search key (e.g., "exotics")
        copartHomePage.enterSearchKey(searchKey);
        copartHomePage.waitForSpinnerToComeAndGo();

        // THEN the text of the resulting search results table contains the expected search result (e.g., "PORSCHE")
        assertTrue(copartHomePage.getTableText().contains(expectedSearchResult));
    }
}