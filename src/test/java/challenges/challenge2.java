package challenges;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class challenge2 extends BaseTests {

    private final static String searchKey = "exotics";
    private final static String expectedSearchResult = "PORSCHE";

    @Test
    public void textSearchForExotics() {
        initCopartHomePage();
        copartHomePage.enterSearchKey(searchKey);
        copartHomePage.waitForSpinnerToComeAndGo();
        assertTrue(copartHomePage.getTableText().contains(expectedSearchResult));
    }
}