package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model base class for GoogleHomePage and CopartHomePage
 */
public class WebPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final String url;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * @param driver an instance of a Selenium WebDriver to test with
     * @param wait   a WebDriverWait instance, with some pre-set time to wait
     * @param url    the URL of a Webpage (e.g., "https://www.google.com/" or "https://www.copart.com")
     */
    public WebPage(WebDriver driver, WebDriverWait wait, String url) {
        this.driver = driver;
        this.wait = wait;
        this.url = url;

        driver.get(url);
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