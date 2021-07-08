package utils;

import org.apache.commons.text.WordUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SeleniumUtils(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean goToPageAndCheckTitle(String url, String linkText) {
        String oldTitle = driver.getTitle();
        driver.get(url);
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs(oldTitle)));  // Wait for page title to change

        String linkTextLower = linkText.toLowerCase(); // "F-150" -> "f-150"
        String linkTextUpper = linkText.toUpperCase();
        String linkTextMixed = linkText.substring(0, 1).toUpperCase() + linkText.substring(1).toLowerCase(); // "CHEVROLET" -> "Chevrolet"
        String linkTextDashedTitleCase = WordUtils.capitalizeFully(linkText.replace("-", " ")).
                replace(" ", "-"); // "MERCEDES-BENZ" -> "Mercedes-Benz"

        String newTitle = driver.getTitle();

        // Wait for the page title to contain the link text (or a variation thereof)
        wait.until(ExpectedConditions.or(ExpectedConditions.titleContains(linkText),
                ExpectedConditions.titleContains(linkTextLower),
                ExpectedConditions.titleContains(linkTextUpper),
                ExpectedConditions.titleContains(linkTextMixed),
                ExpectedConditions.titleContains(linkTextDashedTitleCase)));

        String finalTitle = driver.getTitle();
        System.out.printf("Page title -- originally: %s, before wait: %s, after wait: %s%n",
                oldTitle, newTitle, finalTitle);

        return (finalTitle.toLowerCase().contains(linkTextLower));
    }
}
