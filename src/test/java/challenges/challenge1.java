package challenges;

import base.BaseTests;
import org.testng.annotations.Test;

public class challenge1 extends BaseTests {

    @Test
    public void testGoogleTitle() {
        goToGoogle();
        assertTitleAsExpected("Google");
    }
}