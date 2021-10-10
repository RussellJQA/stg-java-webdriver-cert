package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model base class for GoogleHomePage and CopartHomePage
 */
public class WebPage {
    protected final WebDriver driver;
    protected final String url;
    protected final WebDriverWait wait;
    protected final WebDriverWait longWait;

    private static final long PAGE_LOAD_TIMEOUT = 60;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * @param driver an instance of a Selenium WebDriver to test with
     * @param wait   a WebDriverWait instance, with some pre-set time to wait
     * @param url    the URL of a Webpage (e.g., "https://www.google.com/" or "https://www.copart.com")
     */
    public WebPage(WebDriver driver, String url, WebDriverWait wait, WebDriverWait longWait) {
        this.driver = driver;
        this.url = url;
        this.wait = wait;
        this.longWait = longWait;

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            System.out.printf("Page did not load within %d seconds!%n", PAGE_LOAD_TIMEOUT);
        }
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * Waits for the page title to contain the specified text
     *
     * @param text text which the page title must contain before the waiting period expires
     */
    public void waitForTitleToContain(String text) {
        wait.until(ExpectedConditions.titleContains(text));
    }

    // ----------------------------------------------------------------------
    // Protected instance methods
    // ----------------------------------------------------------------------

    /**
     * Clicks the link with the specified link text
     *
     * @param linkText link text of the link to be clicked
     */
    protected void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }
}