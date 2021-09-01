// This class is a base class for other tests

package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.CopartHomePage;
import pages.GoogleHomePage;
import utils.Screenshots;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseWebDriverTests extends BaseTests {

    protected WebDriverWait wait;
    protected GoogleHomePage googleHomePage;
    protected CopartHomePage copartHomePage;

    private WebDriver driver;
    private String url;

    // PRIVATE STATIC METHODS

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Prevent Chrome from displaying the "Chrome is being controlled by automated test software." notification bar
        // See https://help.applitools.com/hc/en-us/articles/360007189411--Chrome-is-being-controlled-by-automated-test-software-notification
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        // TODO: Conditionally add the following for Selenium 4 (which supports it)
        //      See How to remove the infobar “Microsoft Edge is being controlled by automated test software” in selenium test
        //      at https://codersatellite.com/question-with-identifier-59299282
        // options.setExperimentalOption("useAutomationExtension", false);
        // options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        return options;
    }

    // PUBLIC CLASS METHODS

    /**
     * This function will execute before each <class> tag in test*.xml
     * <p>
     * After this @BeforeClass startClass() method completes, the tests within the specified class will start executing.
     *
     * @param browserType The type of browser ("chrome", "edge", or "firefox") to use for the test
     * @throws IllegalArgumentException Exception thrown when parameter browserType is invalid
     */
    @BeforeClass
    @Parameters({"browserType", "testUrl"}) // Parameter(s) passed from test*.xml file
    public void startClass(String browserType, String testUrl) throws IllegalArgumentException {

        System.out.println("Browser type: " + browserType);

        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup(); // Setup and create Chrome instance
            driver = new ChromeDriver(getChromeOptions());
        } else if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();  // Setup and create Edge instance
            driver = new EdgeDriver(getEdgeOptions());
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); // Setup and create Firefox instance
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException(String.format("Unsupported browser type: %s", browserType));
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        url = testUrl;
    }

    @AfterClass
    public void stopClass() {
        driver.quit();  // Close the browser and [unlike driver.close()] end the session
    }

    public void initGoogleHomePage() {
        googleHomePage = new GoogleHomePage(driver, wait, url);
    }

    public void initCopartHomePage() {
        copartHomePage = new CopartHomePage(driver, wait, url);
    }

    @AfterMethod
    public void stopMethod(ITestResult result) throws InterruptedException {

        // TODO: To avoid sleep() calls in production test code, make this conditional upon an environment variable setting
        // Wait long enough for the state of the browser to be seen before closing the browser.
        Thread.sleep(5000);

        if (ITestResult.FAILURE == result.getStatus()) {
            Screenshots screenshots = new Screenshots(driver);
            String filepath = String.format("screenshots/%s.png", result.getName());
            screenshots.takeScreenshot(filepath, true);
        }
    }

    public void assertTitleAsExpected(String expectedTitle) {
        String title = driver.getTitle();
        assertEquals(title, expectedTitle, String.format("Incorrect page title: %s", title));
    }

    public void assertTitleContains(String expectedTitleSubstring) {
        String title = driver.getTitle();
        assertTrue(title.contains(expectedTitleSubstring), String.format("Incorrect page title: %s", title));
    }

    public String getActualUrl(String href) {
        driver.get(href);
        return driver.getCurrentUrl();
    }
}