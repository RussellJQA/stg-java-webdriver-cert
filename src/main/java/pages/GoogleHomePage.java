// This class contains GoogleSearchPage, the page object for Google's search page

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomePage {

    // PRIVATE VARIABLES

    private final WebDriver driver;
    private final WebDriverWait wait;

    // CONSTRUCTOR

    public GoogleHomePage(WebDriver driver, WebDriverWait wait, String googleUrl) {
        this.driver = driver;
        this.wait = wait;
        driver.get(googleUrl);
    }

    // PUBLIC CLASS METHODS

    // TODO: Move this into a new parent class (WebPage)?
    public void waitForTitleToContain(String text) {
        // Wait for the page title to contain the specified text
        wait.until(ExpectedConditions.titleContains(text));
    }

    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}