package challenges;

/*
Challenge 7:
Take a look at https://www.copart.com main page.
Go to the the Makes/Models section of the page.
Create a 2 dimensional array or arraylist that stores all the values displayed on the page along w/ the URL for that link.
Once you have this array, you can verify that all the elements in the array navigate to the correct page.
*/

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class challenge7 extends BaseTests {

    @Test
    public void checkUrlsOfMostPopularItems() {
        initCopartHomePage();

        List<WebElement> mostPopularItems = copartHomePage.getMostPopularItems3();
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
            String linkText = popularItem.get(0);
            String url = popularItem.get(1);
            System.out.printf("Make or model: %s, url: %s%n", linkText, url);
            goToPageAndCheckTitle(url, linkText);
        }
    }
}