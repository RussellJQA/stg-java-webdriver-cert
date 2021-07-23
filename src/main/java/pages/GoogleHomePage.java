package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private static final String URL = "https://www.google.com/";
    private final WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL);
    }

    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}