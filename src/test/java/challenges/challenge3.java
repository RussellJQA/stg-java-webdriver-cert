package challenges;

import base.BaseWebDriverTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Challenge 3 (Loops):
 * 1. Go to a Copart homepage  (e.g., https://www.copart.com, https://www.copart.ca, or "https://www.copart.co.uk/")
 * 2. Go to the Makes/Models section of the page.
 * This section used to be displayed on initial page load, but now, you first need to click the "Trending" tab.
 * Then you'll find this section there under the "Most Popular Items" heading.
 * 3. Print a list of the vehicle Make/Models, and the URL/href for each type.
 * Using a loop will make sure that everything is displayed regardless of the list size.
 */
public final class challenge3 extends BaseWebDriverTests {

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * Prints the make/model and URL/href of each item listed in a Copart homepage's "Most Popular Items" section
     */
    @Test(priority = 5)
    public void testGetListOfMostPopularItems() {

        // ----------------------------------------------------------------------
        // GIVEN the Copart homepage is displayed
        // ----------------------------------------------------------------------

        initCopartHomePage();

        // ----------------------------------------------------------------------
        // WHEN you get a list of the Web elements for the page's Most Popular Items
        // ----------------------------------------------------------------------

        List<WebElement> mostPopularItems = copartHomePage.getMostPopularItems();
        mostPopularItems.sort(Comparator.comparing(WebElement::getText));

        // ----------------------------------------------------------------------
        // THEN you can print the link text and href for element of the list
        // ----------------------------------------------------------------------

        System.out.println();
        for (WebElement element : mostPopularItems) {
            System.out.println(element.getText() + " - " + element.getAttribute("href"));
        }
    }
}