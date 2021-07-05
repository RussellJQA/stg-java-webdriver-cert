package challenges;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class challenge6 extends BaseTests {

    private final static String searchKey = "nissan";
    private final static String filterPanelLinkText = "Model";
    private final static String filterText = "skyline";
    private final static String filterCheckBox = "Skyline";

    @Test
    public void testChallenge6() {
        initCopartHomePage();
        copartHomePage.search(searchKey);
        Assert.assertTrue(copartHomePage.setFilterTextAndCheckBox(filterPanelLinkText, filterText, filterCheckBox));
    }
}