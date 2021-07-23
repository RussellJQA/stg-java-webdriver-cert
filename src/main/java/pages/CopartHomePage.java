package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Screenshots;

import java.util.List;
import java.util.Map;

public class CopartHomePage {

    // PRIVATE VARIABLES AND METHODS

    private static final String URL = "https://www.copart.com";
    // Java 9's Map.of() is an immutable map with at most 10 key/value pairs
    private static final Map<String, String> columnXpathLocators = Map.of(
            "make", "//span[@class='make-items']//a",
            "model", "//span[@data-uname='lotsearchLotmodel' and not(text()='[[ lm ]]')]",
            "damage", "//span[@data-uname='lotsearchLotdamagedescription' and not(text()='[[ dd ]]')]"
    );
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CopartHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(URL);
    }

    // PUBLIC METHODS

    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public List<WebElement> getElementsFromColumn(String columnName) {
        return driver.findElements(By.xpath(columnXpathLocators.get(columnName)));
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
        }

        waitForSpinnerToComeAndGo();  // Sometimes this fails. Look at alternatives discussed in "class" and afterwards
    }

    public void search(String searchKey) {
        searchAndSetEntriesPerPage(searchKey, -1);  // Leave entriesPerPage unchanged
    }

    public List<WebElement> getMostPopularItems() {
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

    public void checkFilterCheckBox(String filterCheckBoxXPath) {
        WebElement filterCheckBox = driver.findElement(By.xpath(filterCheckBoxXPath));
        filterCheckBox.click();
    }

    public boolean setFilterTextAndCheckBox(String filterPanelLinkText, String filterText, String filterCheckBox) {
        boolean success = true;

        try {
            String filterButtonXPath = String.format("//h4[@class='panel-title']/a[text()='%s']", filterPanelLinkText);
            clickFilterBtn(filterButtonXPath);
            setFilterTextBox(String.format("%s/ancestor::li//form//input", filterButtonXPath), filterText);
            checkFilterCheckBox(String.format("%s/ancestor::li//ul//input[@value='%s']", filterButtonXPath,
                    filterCheckBox));
        } catch (Exception e) {

            // In order to more generally handle test failures,
            // I've added a call to takeScreenshot() as an @AfterMethod in BaseTest.
            // As a result, the following is now redundant, in that a failure here will actually take 2 screenshots.
            // But it was left here in order to show the specified behavior for this challenge.

            Screenshots screenshots = new Screenshots(driver);
            String fileBase = String.format("filtering_for_%s_%s_%s", filterPanelLinkText, filterText, filterCheckBox);
            screenshots.takeScreenshot(String.format("screenshots/%s.png", fileBase));
            String errorMessage = String.format("Filter checkbox for panel: %s, text: %s, checkbox: %s not found.",
                    filterPanelLinkText, filterText, filterCheckBox);
            System.out.println(errorMessage);
            System.out.println(e.getMessage());
            success = false;
        }

        return success;
    }
}