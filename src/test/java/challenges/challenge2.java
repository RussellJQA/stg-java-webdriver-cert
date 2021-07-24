package challenges;

/*
Challenge 2 (Asserts):
Go to the https://www.copart.com main page.
Search for "exotics".
Verify "PORSCHE" is in the list of cars.
Use the hard assertion for this challenge.
*/

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class challenge2 extends BaseTests {

    private final static String searchKey = "exotics";
    private final static String expectedSearchResult = "PORSCHE";

    @Test(priority = 4)
    public void textSearchForExotics() {
        initCopartHomePage();
        copartHomePage.enterSearchKey(searchKey);
        copartHomePage.waitForSpinnerToComeAndGo();
        assertTrue(copartHomePage.getTableText().contains(expectedSearchResult));
    }
}