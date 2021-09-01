package challenges;

/*
Challenge 6 (Error Handling):
1. Go to the https://www.copart.com main page.
2. Search for "nissan".
3. For the "Model" on the left side filter option, search for "skyline".
4. Look for a checkbox for a "Skyline". This is a rare car that might or might not be in the list for models.
    If the checkbox does not exist to click on, then throw an exception.
    Catch the exception, and take a screenshot of the page, to show what it looks like.
*/

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class challenge6 extends BaseWebDriverTests {

    @DataProvider
    private static Object[][] searchData() {
        // Running this twice: once with "Skyline" and once with "Skyline Gt", because:
        //      Although the challenge only calls for "Skyline", that often fails (as the challenge said it might).
        //      So I'm re-running with a value which for the U.S. usually passes,
        //      to demonstrate that it can pass (when it should). [Both values usually fail in Canada and the U.K.]
        return new Object[][]{
                {"nissan", "Model", "skyline", "Skyline"},
                {"nissan", "Model", "skyline", "Skyline Gt"}
        };
    }

    @Test(priority = 9, dataProvider = "searchData")
    public void testChallenge6(String searchKey, String filterPanelLinkText, String filterText, String filterCheckbox) {

        System.out.println("Filter checkbox: " + filterCheckbox);

        // GIVEN the Copart homepage is displayed
        initCopartHomePage();

        // WHEN the user searches for the specified search phrase
        copartHomePage.search(searchKey);

        /*
         * THEN the user is able to successfully do the following in the page's page's left-hand 'Filter Options' sidebar:
         *  - Click the panel with the specified link text (e.g., 'Model')
         *  - Enter the specified text (e.g. 'skyline') in the 'Model' filter panel's text box
         *  - Check the specified checkbox (e.g. 'Skyline') in the 'Model' filter panel's list of checkboxes
         */
        assertTrue(copartHomePage.setFilterTextAndCheckBox(filterPanelLinkText, filterText, filterCheckbox));
    }
}