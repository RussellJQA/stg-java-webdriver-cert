package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private final static String url = "https://www.google.com/";
    private final WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
    }

    public void enterSearchKey(String searchKey) {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchKey, Keys.ENTER);
    }
}