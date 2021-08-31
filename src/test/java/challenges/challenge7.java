package challenges;

/*
Challenge 7 (Array or ArrayList):
1. Go to the https://www.copart.com main page.
2. Go to the Makes/Models section of the page.
    This used to be displayed on initial page load.
    But now, you first need to click the "Trending" tab. There, you'll find it under the "Most Popular Items" heading.
3. Create a 2-dimensional Array or ArrayList that stores all the values displayed on the page along with the URL for that link.
4. Verify that all the elements in the Array or ArrayList navigate to the correct page.
*/

import base.BaseWebDriverTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class challenge7 extends BaseWebDriverTests {

    @Test(priority = 10)
    public void testCheckUrlsOfMostPopularItems() {

        // GIVEN the Copart homepage is displayed
        initCopartHomePage();

        // WHEN you get a list of the link text and the hrefs for the page's "Most Popular Items", and navigate to each href in the list
        List<List<String>> mostPopularItemsLinkTextAndHref = copartHomePage.getMostPopularItemsLinkTextAndHref();

        System.out.println("\n**********");
        SoftAssert softassert = new SoftAssert();
        for (List<String> popularItem : mostPopularItemsLinkTextAndHref) {

            String make = popularItem.get(0);
            String href = popularItem.get(1);
            System.out.printf("Make or model: %s, href: %s%n", make, href);
            String actualUrl = getActualUrl(href);

            String make1 = make.toLowerCase(); // Convert to lowercase to match URL
            String make2 = make1.replace(" ", "-");  // Handle US's "3 SERIES"
            String make3 = make1.replace(" ", ""); // Handle UK's "LAND ROVER" and "MERCEDES BENZ"
            String make4 = make1.replace("-", ""); // Handle Canada's "MERCEDES-BENZ"

            // THEN for each element in the list, the current URL of the navigated-to page contains the element's link text
            softassert.assertTrue((actualUrl.contains(make1) || actualUrl.contains(make2) ||
                            actualUrl.contains(make3) || actualUrl.contains(make4)),
                    String.format("Actual URL (%s) doesn't contain Make '%s'", actualUrl, make));
        }
        softassert.assertAll();
    }
}