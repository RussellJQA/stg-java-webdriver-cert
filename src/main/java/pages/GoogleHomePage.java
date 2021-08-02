// This class contains GoogleSearchPage, the page object for Google's search page

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private final WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        String url = "https://www.google.com/";

        this.driver = driver;
        driver.get(url);
    }

    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}