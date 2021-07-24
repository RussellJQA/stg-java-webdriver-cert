package challenges;

/*
Challenge 3 (Loops):
1. Go to the https://www.copart.com main page.
2. Go to the the Makes/Models section of the page.
    This used to be displayed on initial page load.
    But now, you first need to click the "Trending" tab. There, you'll find it under the "Most Popular Items" heading.
3. Print a list of the vehicle Make/Models, and the URL/href for each type.
    This list can dynamically change depending on what is authored by the content creator,
    but using a loop will make sure that everything will be displayed regardless of the list size.
*/

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class challenge3 extends BaseTests {

    @Test(priority = 5)
    public void getListOfMostPopularItems() {
        initCopartHomePage();

        List<WebElement> mostPopularItems = copartHomePage.getMostPopularItems();
        mostPopularItems.sort(Comparator.comparing(WebElement::getText));

        for (WebElement element : mostPopularItems) {
            System.out.println(element.getText() + " - " + element.getAttribute("href"));
        }
    }
}