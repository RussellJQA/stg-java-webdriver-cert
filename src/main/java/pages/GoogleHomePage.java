package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {

    private final WebDriver driver;

    private final static String url = "https://www.google.com/";

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
    }
}