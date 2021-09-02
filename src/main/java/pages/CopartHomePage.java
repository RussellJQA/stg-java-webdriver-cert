package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Screenshots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Page object for Copart.com's home page
 */
public final class CopartHomePage extends WebPage {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * @param driver
     * @param wait
     * @param url
     */
    public CopartHomePage(WebDriver driver, WebDriverWait wait, String url) {
        // Possible url values: "https://www.copart.com", "https://www.copart.ca/", "https://www.copart.co.uk/"
        super(driver, wait, url);
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * Prints the count for each of the distinct text values from the specified WebElements
     *
     * @param counts
     * @param testTitle
     */
    public static void printWebElementValueCounts(Map<String, Integer> counts, String testTitle) {
        System.out.println(testTitle);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue());
        }
    }

    /**
     * @param columnValueCounts
     * @param columnLumping
     * @return
     */
    public static Map<String, Integer> getLumpedColumnValueCounts(Map<String, Integer> columnValueCounts,
                                                                  List<String> columnLumping) {
        if (columnLumping.size() < 2) {
            throw new IllegalArgumentException("columnLumping must contain at least 2 elements");
        }

        String lumpMiscAs = columnLumping.get(0); // Lump all unspecified categories together under the lumpMiscAs category
        List<String> nonLumpedColumnValues = columnLumping.subList(1, columnLumping.size());

        // Lump all unspecified damage categories together under the "MISC" category.
        //      A LinkedHashMap maintains insertion order, so that "MISC" appears last, preceded by the others in alphabetical order
        Map<String, Integer> lumpedColumnValueCounts = new LinkedHashMap<>();
        int miscCount = 0;
        for (Map.Entry<String, Integer> valueCount : columnValueCounts.entrySet()) {
            String key = valueCount.getKey();
            if (nonLumpedColumnValues.contains(key)) {
                lumpedColumnValueCounts.put(key, valueCount.getValue());
            } else {
                miscCount += columnValueCounts.get(key); // Merge category count into count for "MISC" category
            }
        }
        lumpedColumnValueCounts.put(lumpMiscAs, miscCount);
        return lumpedColumnValueCounts;
    }

    /**
     * Gets counts for each of the distinct text values from the specified WebElements
     *
     * @param elements
     * @return
     */
    public static Map<String, Integer> getWebElementValueCounts(List<WebElement> elements) {
        // Use TreeMap (an implementation of the SortedMap interface), rather than HashMap.
        //      (TreeMap is automatically sorted in ascending order of the map's keys.)
        Map<String, Integer> columnValueCounts = new TreeMap<>();

        for (WebElement element : elements) {
            String elementKey = element.getText();
            Integer count = columnValueCounts.get(elementKey);
            columnValueCounts.put(elementKey, (count == null) ? 1 : count + 1);
        }

        return columnValueCounts;
    }

    /**
     * Returns the XPath used directly for the filter button in the page's left-hand 'Filter Options' sidebar
     *
     * @param panelLinkText
     * @return
     */
    private static String filterButtonXpath(String panelLinkText) {
        // XPath used directly for the filter button in the page's left-hand 'Filter Options' sidebar
        //  and also used indirectly for the corresponding filter text box and filter checkboxes

        return String.format("//h4[@class='panel-title']/a[text()='%s']", panelLinkText);
    }

    /**
     * Enters the specified search key into main search input and press RETURN
     *
     * @param searchKey
     */
    public void enterSearchKey(String searchKey) {
        driver.findElement(By.id("input-search")).sendKeys(searchKey, Keys.ENTER);
    }

    /**
     * Waits for the progress spinner to be present and then become invisible
     */
    public void waitForSpinnerToComeAndGo() {
        By spinnerLoc = By.xpath("//div[@id='serverSideDataTable_processing']");
        WebElement spinner = wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLoc));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }

    /**
     * Returns the text from the search results table WebElement
     */
    public String getTableText() {
        //
        return driver.findElement(By.xpath("*//table[@id='serverSideDataTable']")).getText();
    }

    /**
     * Returns a sorted list of the links (WebElements) from the 'Most Popular Items' section of the page's 'Trending' tab
     */
    public List<WebElement> getMostPopularItems() {
        By mostPopularItemLinks = By.xpath("//span[@ng-repeat='popularSearch in popularSearches']/a");

        clickLink("Trending");

        List<WebElement> mostPopularItems = driver.findElements(mostPopularItemLinks);
        mostPopularItems.sort(Comparator.comparing(WebElement::getText));

        return mostPopularItems;
    }

    /**
     * @return
     */
    public List<List<String>> getMostPopularItemsLinkTextAndHref() {

        List<WebElement> mostPopularItems = getMostPopularItems();

        // Create a 2 dimensional array or arraylist that stores all the values displayed on the page along w/ the URL for that link
        //      [A map might be preferable.]
        List<List<String>> mostPopularItemsLinkTextAndHref = new ArrayList<>();
        for (WebElement element : mostPopularItems) {
            mostPopularItemsLinkTextAndHref.add(new ArrayList<>(Arrays.asList(element.getText(), element.getAttribute("href"))));
        }

        return mostPopularItemsLinkTextAndHref;
    }

    /**
     * Searches for the specified search key and set the entries per page
     *
     * @param searchKey
     * @param entriesPerPage
     */
    public void searchAndSetEntriesPerPage(String searchKey, int entriesPerPage) {
        enterSearchKey(searchKey);

        if (entriesPerPage > 0) {
            setEntriesPerPageTo(entriesPerPage);
        }

        waitForSpinnerToComeAndGo();  // Sometimes this fails. Look at alternatives discussed in "class" and afterwards
    }

    // ----------------------------------------------------------------------
    // Private instance methods
    // ----------------------------------------------------------------------

    /**
     * Searches for the specified search key and set the entries per page
     *
     * @param columnName
     * @return
     */
    public List<WebElement> getElementsFromColumn(String columnName) {
        // Java 9's Map.of() is an immutable map with at most 10 key/value pairs
        Map<String, String> columnXpathLocators = Map.of(
                "make", "//span[@class='make-items']//a",
                "model", "//span[@data-uname='lotsearchLotmodel' and not(text()='[[ lm ]]')]",
                "damage", "//span[@data-uname='lotsearchLotdamagedescription' and not(text()='[[ dd ]]')]"
        );

        return driver.findElements(By.xpath(columnXpathLocators.get(columnName)));
    }

    /**
     * @param searchKey
     */
    public void search(String searchKey) {
        searchAndSetEntriesPerPage(searchKey, -1);  // Leave entriesPerPage unchanged
    }

    /**
     * Checks the specified checkbox. For example, check the 'Model' filter's 'Skyline' checkbox
     *
     * @param panelLinkText
     * @param filterCheckBox
     */
    public void checkFilterCheckBox(String panelLinkText, String filterCheckBox) {
        String xpath = String.format("%s/ancestor::li//ul//input[@value='%s']", filterButtonXpath(panelLinkText), filterCheckBox);
        driver.findElement(By.xpath(xpath)).click();
    }

    // ----------------------------------------------------------------------
    // Public class (static) methods
    // ----------------------------------------------------------------------

    /**
     * In the page's page's left-hand 'Filter Options' sidebar:
     * - Click the panel with the specified link text (e.g., 'Model')
     * - Enter the specified text (e.g. 'skyline') in the 'Model' filter panel's text box
     * - Check the specified checkbox (e.g. 'Skyline') in the 'Model' filter panel's list of checkboxes
     * &
     *
     * @param filterPanelLinkText
     * @param filterText
     * @param filterCheckBox
     * @return
     */
    public boolean setFilterTextAndCheckBox(String filterPanelLinkText, String filterText, String filterCheckBox) {
        boolean success = true;

        try {
            // Click the panel with the specified link text (e.g., 'Model')
            clickFilterBtn(filterPanelLinkText);

            // Enter the specified text (e.g. 'Skyline') in the corresponding filter text box
            setFilterTextBox(filterPanelLinkText, filterText);

            // Check the corresponding filter check box
            checkFilterCheckBox(filterPanelLinkText, filterCheckBox);
        } catch (Exception e) {

            // In order to more generally handle test failures,
            // I've added a call to takeScreenshot() as an @AfterMethod in BaseTest.
            // As a result, the following is now redundant, in that a failure here will actually take 2 screenshots.
            // But it was left here in order to show the specified behavior for this challenge.

            // Screenshot functionality for Challenge 6
            Screenshots screenshots = new Screenshots(driver);
            String fileBase = String.format("filtering_for_%s_%s_%s", filterPanelLinkText, filterText, filterCheckBox);
            screenshots.takeScreenshot(String.format("screenshots/%s.png", fileBase), true);
            String errorMessage = String.format("Filter checkbox for panel: %s, text: %s, checkbox: %s not found.",
                    filterPanelLinkText, filterText, filterCheckBox);
            System.out.println(errorMessage);
            System.out.println(e.getMessage());
            success = false;
        }

        return success;
    }

    /**
     * Enters the specified filter text (e.g., 'skyline') in the specified
     * filter text box (e.g., the filter panel's 'Model' text box)
     *
     * @param panelLinkText
     * @param filterText
     */
    private void setFilterTextBox(String panelLinkText, String filterText) {
        String xpath = String.format("%s/ancestor::li//form//input", filterButtonXpath(panelLinkText));
        driver.findElement(By.xpath(xpath)).sendKeys(filterText);
    }

    /**
     * Clicks the specified filter button in the page's left-hand 'Filter Options' sidebar
     * For example, click the button which expands the 'Model' filter
     *
     * @param panelLinkText
     */
    private void clickFilterBtn(String panelLinkText) {
        String xpath = filterButtonXpath(panelLinkText);
        driver.findElement(By.xpath(xpath)).click();
    }

    // ----------------------------------------------------------------------
    // Private class (static) methods
    // ----------------------------------------------------------------------

    /**
     * Sets the entries per page, using the 'Show {20/50/100} entries' dropdown selector
     *
     * @param desiredEntriesPerPage
     */
    private void setEntriesPerPageTo(int desiredEntriesPerPage) {

        // The "Show {20/50/100} entries" dropdown selector
        By numEntriesSelector = By.name("serverSideDataTable_length");

        WebElement entriesPerPageElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(numEntriesSelector));

        // Change the selected number of entries to display per page
        Select entriesPerPage = new Select(entriesPerPageElement);
        entriesPerPage.selectByValue(String.valueOf(desiredEntriesPerPage));
    }
}