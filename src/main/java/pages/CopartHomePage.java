package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CopartHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final static String url = "https://www.copart.com";

    public CopartHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(url);
    }

    public void enterSearchKey(String searchKey) { driver.findElement(By.id("input-search")).sendKeys(searchKey, Keys.ENTER); }
    public String getTableText() { return driver.findElement(By.xpath("*//table[@id='serverSideDataTable']")).getText(); }

    public void waitForSpinnerToComeAndGo() {
        By spinnerLoc = By.xpath("//div[@id='serverSideDataTable_processing']");
        WebElement spinner = wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLoc));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }
}