package challenges;

/*
Challenge 1 (Java/WebDriver/textNG):
1. Add a test method to go to the https://www.google.com main page.
2. Add another test method to verify that the page title is what we expected, by using the testNG Assert.

A 3rd test has been added, to borrow 2 additional challenge requirements from the Python/WebDriver Level 1 certification.

testGotoGoogle() can be run separately.
But the other 2 tests are designed to be run together as part of class challenge1, as when selecting
(from the right-click context menu for challenge1, in IntelliJ):
• Run 'challenge1'
*/

import base.BaseTests;
import org.testng.annotations.Test;

public class challenge1 extends BaseTests {

    private final static String searchKey = "puppies";

    @Test(priority = 1)
    public void testGotoGoogle() {
        initGoogleHomePage();
    }

    @Test(priority = 2)
    public void testGoogleTitle() {
        assertTitleAsExpected("Google");
    }

    @Test(priority = 3)
    public void testSearchPuppies() {
        // The STG Python/WebDriver Level 1 (Automation Associate) certification includes 2 additional requirements for challenge 1:
        // • Search for “puppies”
        // • Assert that the results page that loads has “puppies” in its title
        // So, for comparison between Java and Python, that has been implemented below.
        googleHomePage.enterSearchKey(searchKey);
        assertTitleContains(searchKey);
    }
}