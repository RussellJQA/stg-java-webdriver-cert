package challenges;

/*
Challenge 7:
Go to the https://www.copart.com main page.
Go to the the Makes/Models section of the page.
    This used to be displayed on initial page load.
    But now, you first need to click the "Trending" tab. There, you'll find it under the "Most Popular Items" heading.
Create a 2 dimensional Array or ArrayList that stores all the values displayed on the page along with the URL for that link.
Once you have this array, verify that all the elements in the Array navigate to the correct page.
*/

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class challenge7 extends BaseTests {

    @Test(priority = 10)
    public void checkUrlsOfMostPopularItems() {
        initCopartHomePage();

        List<WebElement> mostPopularItems = copartHomePage.getMostPopularItems();
        mostPopularItems.sort(Comparator.comparing(WebElement::getText));

        // Create a 2 dimensional array or arraylist that stores all the values displayed on the page along w/ the URL for that link
        //      [A map might be preferable.]
        ArrayList<ArrayList<String>> popularItemList = new ArrayList<>();
        for (WebElement element : mostPopularItems) {
            popularItemList.add(new ArrayList<>(Arrays.asList(element.getText(), element.getAttribute("href"))));
        }

        // Once you have this array, you can then verify that all the elements in the array navigate to the correct page
        System.out.println("\n**********");
        for (ArrayList<String> popularItem : popularItemList) {
            String make = popularItem.get(0);
            String href = popularItem.get(1);
            System.out.printf("Make or model: %s, href: %s%n", make, href);
            String actualUrl = getActualUrl(href);
            assertTrue(actualUrl.contains(make.toLowerCase()),
                    String.format("Actual URL (%s) doesn't contain Make '%s'", actualUrl, make));
        }
    }
}