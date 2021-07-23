package challenges;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class challenge3 extends BaseTests {

    @Test
    public void getListOfMostPopularItems() {
        initCopartHomePage();

        List<WebElement> mostPopularItems = copartHomePage.getMostPopularItems();
        mostPopularItems.sort(Comparator.comparing(WebElement::getText));

        for (WebElement element : mostPopularItems) {
            System.out.println(element.getText() + " - " + element.getAttribute("href"));
        }
    }
}