package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The base class for GoogleHomePage and CopartHomePage
 */
public class WebPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final String url;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * @param driver
     * @param wait
     * @param url
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
     * @param text
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
     * @param linkText
     */
    protected void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }
}