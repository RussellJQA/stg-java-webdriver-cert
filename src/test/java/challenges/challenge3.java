package challenges;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.*;

// Sort WebElements in ascending order by WebElement.getText()
//class SortByLinkText implements Comparator<WebElement> {
//    public int compare(WebElement element1, WebElement element2) {
//        return element1.getText().compareTo(element2.getText());
//    }
//}

public class challenge3 extends BaseTests {

    @Test
    public void getListOfMostPopularItems() {

        goToCopart();

        List<WebElement> mostPopularItems = copartHomePage.getMostPopularItems3();

        // Collections.sort(mostPopularItems, new SortByLinkText());  // Replaced by a simpler List sort below
        mostPopularItems.sort(Comparator.comparing(WebElement::getText));

        for (WebElement element : mostPopularItems) {
            System.out.println(element.getText() + " - " + element.getAttribute("href"));
        }
    }
}