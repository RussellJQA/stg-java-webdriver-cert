// This class contains GoogleSearchPage, the page object for Google's search page

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private final WebDriver driver;

    public GoogleHomePage(WebDriver driver, String googleUrl) {
        this.driver = driver;
        driver.get(googleUrl);
    }

    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}