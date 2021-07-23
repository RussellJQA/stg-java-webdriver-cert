package challenges;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class challenge6 extends BaseTests {

    private final static String searchKey = "nissan";
    private final static String filterPanelLinkText = "Model";
    private final static String filterText = "skyline";
    private final static String filterCheckBox = "Skyline";

    @Test
    public void testChallenge6() {
        initCopartHomePage();
        copartHomePage.search(searchKey);
        assertTrue(copartHomePage.setFilterTextAndCheckBox(filterPanelLinkText, filterText, filterCheckBox));
    }
}