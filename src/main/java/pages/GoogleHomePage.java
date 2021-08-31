// This class contains GoogleSearchPage, the page object for Google's search page

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomePage extends WebPage {

    // CONSTRUCTOR

    public GoogleHomePage(WebDriver driver, WebDriverWait wait, String url) {
        // Possible url values: "https://www.google.com/", "https://www.google.ca/", "https://www.google.co.uk/"
        super(driver, wait, url);
    }

    // PUBLIC CLASS METHODS

    public void enterSearchKey(String searchKey) {
        // Enter the specified search key into main search input and press RETURN
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}