package challenges;

/*
Challenge 6 (Error Handling):
1. Go to the https://www.copart.com main page.
2. Search for "nissan".
3. For the "Model" on the left side filter option, search for "skyline".
4. Look for a check box for a "Skyline". This is a rare car that might or might not be in the list for models.
    If the checkbox does not exist to click on, then throw an exception.
    Catch the exception, and take a screenshot of the page, to show what it looks like.
*/

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class challenge6 extends BaseTests {

    private final static String searchKey = "nissan";
    private final static String filterPanelLinkText = "Model";
    private final static String filterText = "skyline";
    private final static String filterCheckBox = "Skyline";

    @Test(priority = 9)
    public void testChallenge6() {
        initCopartHomePage();
        copartHomePage.search(searchKey);
        assertTrue(copartHomePage.setFilterTextAndCheckBox(filterPanelLinkText, filterText, filterCheckBox));
    }
}