package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final String url;

    // PROTECTED CLASS METHODS

    public WebPage(WebDriver driver, WebDriverWait wait, String url) {
        this.driver = driver;
        this.wait = wait;
        this.url = url;
        driver.get(url);
    }

    // CONSTRUCTOR

    protected void clickLink(String linkText) {
        // Click the link with the specified link text
        driver.findElement(By.linkText(linkText)).click();
    }

    // PUBLIC CLASS METHODS

    public void waitForTitleToContain(String text) {
        // Wait for the page title to contain the specified text
        wait.until(ExpectedConditions.titleContains(text));
    }
}