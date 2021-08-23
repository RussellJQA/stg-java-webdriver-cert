package challenges;

/*
Challenge 1 (Java/WebDriver/textNG):
1. Add a test method to go to the https://www.google.com main page.
2. Add another test method to verify that the page title is what we expected, by using the testNG Assert.

A 3rd test has been added, to borrow 2 additional challenge requirements from the Python/WebDriver Level 1 certification.

Although testGotoGoogle() can be run separately, the other 2 tests both "depend" on it being run first.
Such dependencies are enforced using testNG's "dependsOnMethods" attribute.
*/

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class challenge1 extends BaseWebDriverTests {

    @DataProvider
    public Object[][] searchData() {
        return new Object[][]{{"puppies"}};
    }

    // GIVEN/WHEN the Google search page is displayed
    @Test(priority = 1)
    public void testGotoGoogle() {
        initGoogleHomePage();
    }

    // THEN the page title is "Google"
    @Test(priority = 1, dependsOnMethods = "testGotoGoogle")
    public void testGoogleTitle() {
        assertTitleAsExpected("Google");
    }

    @Test(priority = 2, dependsOnMethods = "testGotoGoogle", dataProvider = "searchData")
    public void testSearchPuppies(String searchKey) {
        // The STG Python/WebDriver Level 1 (Automation Associate) certification includes 2 additional requirements for challenge 1:
        // • Search for the specified search key (e.g., "puppies")
        // • Assert that the results page that loads has the specified search key in its title
        // So, for comparison between Java and Python, that has been implemented below.

        // WHEN the user searches for the specified search phrase
        googleHomePage.enterSearchKey(searchKey);

        // THEN the page title of the search results contains the specified search phrase
        assertTitleContains(searchKey);
    }
}