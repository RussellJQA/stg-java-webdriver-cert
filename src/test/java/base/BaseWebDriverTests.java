package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
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

import java.time.Duration;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Base class for WebDriver-based tests
 */
public class BaseWebDriverTests extends BaseTests {

    protected WebDriverWait wait;
    protected WebDriverWait longWait;
    protected GoogleHomePage googleHomePage;
    protected CopartHomePage copartHomePage;
    protected int SecondsToSleepBeforeWebDriverQuit;

    private WebDriver driver;
    private String url;

    private static final long WAIT_TIMEOUT = 10; // Duration of normal waits in seconds
    private static final long LONG_WAIT_TIMEOUT = 20; // Duration of long waits in seconds

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * This function will be run before the first test method in the current class (as specified by a <class> tag in a test*.xml file) is invoked.
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
            ChromeOptions options = new ChromeOptions();

            // Prevent Chrome from displaying the "Chrome is being controlled by automated test software." infobar
            // See https://help.applitools.com/hc/en-us/articles/360007189411--Chrome-is-being-controlled-by-automated-test-software-notification
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            driver = new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();  // Setup and create Edge instance
            EdgeOptions options = new EdgeOptions();

            // Because EdgeOptions.setExperimentalOption() wasn't introduced into Selenium until Selenium 4,
            // the following code requires Selenium 4+ not just to run, but even to compile.
            //
            // Hide Edge's "... controlled by automated test software" infobar.
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            driver = new EdgeDriver(options);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); // Setup and create Firefox instance
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException(String.format("Unsupported browser type: %s", browserType));
        }

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        SecondsToSleepBeforeWebDriverQuit = Integer.parseInt(dotenv.get("SECONDS_TO_SLEEP_BEFORE_WEBDRIVER_QUIT", "0"));

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT)); // Selenium 3.141.59's "WebDriverWait(driver, 10)" deprecated in Selenium 4
        longWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_WAIT_TIMEOUT));
        url = testUrl;
    }

    /**
     * This function will be run after all the test methods in the current class (as specified by a <class> tag in a test*.xml file) have been run.
     *
     * @throws InterruptedException If the SecondsToSleepBeforeWebDriverQuit environment variable is non-zero,
     *                              the function sleeps for the specified number of seconds and then throws InterruptedException
     */
    @AfterClass
    public void stopClass() throws InterruptedException {

        // Only do this when the corresponding environment variable has specifically been set to enable it
        // [as for development or demonstration purposes --
        //  to allow (during test execution) the then current Web page to be observed].
        if (SecondsToSleepBeforeWebDriverQuit != 0) {
            // Wait for the state of the browser to be seen before closing the browser.
            Thread.sleep(1000L * SecondsToSleepBeforeWebDriverQuit);
        }

        driver.quit();  // Close the browser and [unlike driver.close()] end the session
    }

    /**
     * This function will be run after each test method (as specified by a <test> tag in a test*.xml file).
     *
     * @param result The result of the test method, as determined by testNG.
     */
    @AfterMethod
    public void stopMethod(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            Screenshots screenshots = new Screenshots(driver);
            String filepath = String.format("screenshots/%s.png", result.getName());
            screenshots.takeScreenshot(filepath, true);
        }
    }

    /**
     * Displays a Google homepage.
     */
    public void initGoogleHomePage() {
        googleHomePage = new GoogleHomePage(driver, url, wait, longWait);
    }

    /**
     * Displays a Copart homepage.
     */
    public void initCopartHomePage() {
        copartHomePage = new CopartHomePage(driver, url, wait, longWait);
    }

    // ----------------------------------------------------------------------
    // Private class (static) methods
    // ----------------------------------------------------------------------

    /**
     * Asserts that the page title is as expected.
     *
     * @param expectedTitle expected page title
     */
    public void assertTitleAsExpected(String expectedTitle) {
        String title = driver.getTitle();
        assertEquals(title, expectedTitle, String.format("Incorrect page title: %s", title));
    }

    /**
     * Asserts that the page title contains the specified substring.
     *
     * @param expectedTitleSubstring substring expected to be contained in the page title
     */
    public void assertTitleContains(String expectedTitleSubstring) {
        String title = driver.getTitle();
        assertTrue(title.contains(expectedTitleSubstring), String.format("Incorrect page title: %s", title));
    }

    /**
     * Goes to the specified URL, and get the resulting page's actual URL, which sometimes is different
     * (e.g., due to relative URLs or redirection).
     *
     * @param urlToGoTo specified URL to go to
     * @return the resulting page's actual (current) URL
     */
    public String getActualUrl(String urlToGoTo) {
        driver.get(urlToGoTo);
        return driver.getCurrentUrl();
    }
}