// This class is a base class for other tests

package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.CopartHomePage;
import pages.GoogleHomePage;
import utils.Screenshots;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseWebDriverTests extends BaseTests {

    public WebDriverWait wait;
    protected GoogleHomePage googleHomePage;
    protected CopartHomePage copartHomePage;
    private WebDriver driver;

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Prevent Chrome from displaying the "Chrome is being controlled by automated test software." notification bar
        // See https://help.applitools.com/hc/en-us/articles/360007189411--Chrome-is-being-controlled-by-automated-test-software-notification
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

    @BeforeClass
    public void startClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void stopClass() {
        driver.quit();  // Close the browser and [unlike driver.close()] end the session
    }

    @AfterMethod
    public void stopMethod(ITestResult result) throws InterruptedException {

        // Wait long enough for the state of the browser to be seen before closing the browser. {REMOVE FOR PRODUCTION TEST CODE.}
        Thread.sleep(5000);

        if (ITestResult.FAILURE == result.getStatus()) {
            Screenshots screenshots = new Screenshots(driver);
            String filepath = String.format("screenshots/%s.png", result.getName());
            screenshots.takeScreenshot(filepath);
        }
    }

    public void assertTitleAsExpected(String expectedTitle) {
        assertEquals(driver.getTitle(), expectedTitle, "Incorrect page title");
    }

    public void assertTitleContains(String expectedTitleSubstring) {
        assertTrue(driver.getTitle().contains(expectedTitleSubstring), "Incorrect page title");
    }

    public void initGoogleHomePage(String googleUrl) {
        googleHomePage = new GoogleHomePage(driver, googleUrl);
    }

    public void initCopartHomePage(String copartUrl) {
        copartHomePage = new CopartHomePage(driver, wait, copartUrl);
    }

    public String getActualUrl(String href) {
        driver.get(href);
        return driver.getCurrentUrl();
    }
}