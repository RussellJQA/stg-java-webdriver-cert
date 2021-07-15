package challenges;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

// TODO: SonarLint recommends renaming "challenge7" to match the regular expression:
//       ^((Test|IT)[a-zA-Z0-9_]+|[A-Z][a-zA-Z0-9_]*(Test|Tests|TestCase|IT|ITCase))$
// as in: "TestChallenge06"
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