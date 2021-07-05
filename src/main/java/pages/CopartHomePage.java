package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class CopartHomePage {

    // PRIVATE VARIABLES AND METHODS

    private final static String url = "https://www.copart.com";
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Java 9's Map.of() is an immutable map with at most 10 key/value pairs
    private final static Map<String, String> columnXpathLocators = Map.of(
            "make", "//span[@class='make-items']//a",
            "model", "//span[@data-uname='lotsearchLotmodel' and not(text()='[[ lm ]]')]",
            "damage", "//span[@data-uname='lotsearchLotdamagedescription' and not(text()='[[ dd ]]')]"
    );

    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    // PUBLIC METHODS

    public List<WebElement> getElementsFromColumn(String columnName) {
        return driver.findElements(By.xpath(columnXpathLocators.get(columnName)));
    }

    public CopartHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(url);
    }

    public void enterSearchKey(String searchKey) {
        driver.findElement(By.id("input-search")).sendKeys(searchKey, Keys.ENTER);
    }

    public void setEntriesPerPageTo(int desiredEntriesPerPage) {
        WebElement entriesPerPageElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.name("serverSideDataTable_length")));
        Select entriesPerPage = new Select(entriesPerPageElement);
        entriesPerPage.selectByValue(String.valueOf(desiredEntriesPerPage));
    }

    public String getTableText() {
        return driver.findElement(By.xpath("*//table[@id='serverSideDataTable']")).getText();
    }

    public void searchAndSetEntriesPerPage(String searchKey, int entriesPerPage) {
        enterSearchKey(searchKey);

        if (entriesPerPage > 0) {
            setEntriesPerPageTo(entriesPerPage);
        };

        waitForSpinnerToComeAndGo();  // Sometimes this fails. Look at alternatives discussed in "class" and afterwards
    }

    public void search (String searchKey) {
        searchAndSetEntriesPerPage(searchKey, -1);  // Leave entriesPerPage unchanged
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

    public void clickFilterBtn(String filterButtonXPath) {
        WebElement filterButton = driver.findElement(By.xpath(filterButtonXPath));
        filterButton.click();
    }

    public void setFilterTextBox(String filterTextBoxXPath, String filterText) {
        WebElement filterTextBox = driver.findElement(By.xpath(filterTextBoxXPath));
        filterTextBox.sendKeys(filterText);
    }

    public void checkFilterCheckBox(String filterCheckBoxXPath, String successMessage) {
        WebElement filterCheckBox = driver.findElement(By.xpath(filterCheckBoxXPath));
        filterCheckBox.click();
        System.out.println(successMessage);
    }

}