package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object model for a Google home page (e.g., https://www.google.com/)
 */
public final class GoogleHomePage extends WebPage {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * @param driver an instance of a Selenium WebDriver to test with
     * @param wait   a WebDriverWait instance, with some pre-set time to wait
     * @param url    the URL of a Google homepage (e.g., "https://www.google.com/", "https://www.google.ca/", or "https://www.google.co.uk/")
     */
    public GoogleHomePage(WebDriver driver, WebDriverWait wait, String url) {
        super(driver, wait, url);
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * Enters the specified search key into main search input and presses RETURN
     *
     * @param searchKey a word or phrase to search for (for example, "puppies")
     */
    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}