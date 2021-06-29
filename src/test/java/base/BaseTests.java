package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pages.CopartHomePage;
import pages.GoogleHomePage;

public class BaseTests {

    private WebDriver driver;

    protected GoogleHomePage googleHomePage;
    protected CopartHomePage copartHomePage;

    public WebDriverWait wait;

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Prevent Chrome from displaying the "Chrome is being controlled by automated test software." notification bar
        // See https://help.applitools.com/hc/en-us/articles/360007189411--Chrome-is-being-controlled-by-automated-test-software-notification
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

    @BeforeSuite
    public void startSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterSuite
    public void stopSuite () {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() {
    }

    @AfterClass
    public void stopClass() throws InterruptedException {
        Thread.sleep(5000); // Wait long enough for the state of the browser to be seen before closing the browser.
        driver.quit();  // Close the browser and [unlike driver.close()] end the session
    }

    public void assertTitleAsExpected(String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle,"Incorrect page title");
    }

    public void assertTitleContains(String expectedTitleSubstring) {
        Assert.assertTrue(driver.getTitle().contains(expectedTitleSubstring),"Incorrect page title");
    }

    public void assertUrlContains(String expectedTitleSubstring) {
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedTitleSubstring),"Incorrect URL");
    }

    public void goToGoogle() {
        googleHomePage =  new GoogleHomePage(driver);
    }

    public void goToCopart() {
        copartHomePage = new CopartHomePage(driver, wait);
    }
}