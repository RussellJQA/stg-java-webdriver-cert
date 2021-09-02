package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains GoogleSearchPage, the page object for Google's search page
 */
public final class GoogleHomePage extends WebPage {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GoogleHomePage(WebDriver driver, WebDriverWait wait, String url) {
        // Possible url values: "https://www.google.com/", "https://www.google.ca/", "https://www.google.co.uk/"
        super(driver, wait, url);
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * Enters the specified search key into main search input and presses RETURN
     *
     * @param searchKey
     */
    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}