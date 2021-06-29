package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CopartHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final static String url = "https://www.copart.com";

    public CopartHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(url);
    }

    private void clickLink(String linkText) { driver.findElement(By.linkText(linkText)).click(); }

    public void enterSearchKey(String searchKey) { driver.findElement(By.id("input-search")).sendKeys(searchKey, Keys.ENTER); }

    public void setEntriesPerPageTo(int desiredEntriesPerPage) {
        WebElement entriesPerPageElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.name("serverSideDataTable_length")));
        Select entriesPerPage = new Select(entriesPerPageElement);
        entriesPerPage.selectByValue(String.valueOf(desiredEntriesPerPage));
    }

    public String getTableText() { return driver.findElement(By.xpath("*//table[@id='serverSideDataTable']")).getText(); }

    // No longer needed (at least for now).
    // public List<WebElement> getMakes() {return driver.findElements(By.xpath("//span[@class='make-items']//a")); }

    public List<WebElement> getModels() {
        By spanModelLocator = By.xpath("//span[@data-uname='lotsearchLotmodel' and not(text()='[[ lm ]]')]");
        return driver.findElements(spanModelLocator);
    }

    public List<WebElement> getDamages() {
        By spanDamageLocator = By.xpath("//span[@data-uname='lotsearchLotdamagedescription' and not(text()='[[ dd ]]')]");
        return driver.findElements(spanDamageLocator);
    }

    // TODO: Ask - Which of the following getMostPopularItemsX() versions is preferred?

    // This 1st version include2 the links underneath the "Categories" <h3>
   public List<WebElement> getMostPopularItems1() {
        clickLink("Trending"); // Clicking on this tab brings up a list of "Most Popular Items" (Makes/Models)
        return driver.findElements(
                By.xpath("//h3[text()='Most Popular Items']//parent::div//a[not(text()='VIEW MORE')]"));
    }

    // The next 2 versions don't include the links underneath the "Categories" <h3>
    // Neither requires specifically filtering out any "VIEW MORE" links. Even without filtering, neither includes them.

    public List<WebElement> getMostPopularItems2() {
        clickLink("Trending");
        return driver.findElements(By.xpath("//span[@ng-repeat='popularSearch in popularSearches']//a"));
    }

    public List<WebElement> getMostPopularItems3() {
        clickLink("Trending");
        return driver.findElements(By.xpath("//h3[text()='Most Popular Items']//parent::div/div/span/a"));
    }

    public void waitForSpinnerToComeAndGo() {
        By spinnerLoc = By.xpath("//div[@id='serverSideDataTable_processing']");
        WebElement spinner = wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLoc));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }
}