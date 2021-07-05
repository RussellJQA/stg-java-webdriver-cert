package challenges;

import base.BaseTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

public class challenge6 extends BaseTests {

    private final static String searchKey = "nissan";
    private final static String filterText = "skyline";
    private final static String filterCheckBox = "skyline";

//    public void takeScreenshot(String filepath) throws IOException {
//        TakesScreenshot screenshot = ((TakesScreenshot) driver);
//        File image = screenshot.getScreenshotAs(OutputType.FILE);
//        File destination = new File(filepath);
//        FileUtils.copyFile(image, destination);
//    }

    @Test
    public void testChallenge6()  {
        initCopartHomePage();
        copartHomePage.search(searchKey);

        String filterButtonXPath = "//a[@data-uname='ModelFilter']";
        String filterTextBoxXPath = String.format("%s/ancestor::li//form//input", filterButtonXPath);
        String filterCheckBoxXPath = String.format("%s/ancestor::li//ul//input", filterButtonXPath);

        copartHomePage.clickFilterBtn(filterButtonXPath);
        copartHomePage.setFilterTextBox(filterTextBoxXPath, filterText);
        try {
            copartHomePage.checkFilterCheckBox(filterCheckBoxXPath,
                    String.format("Filter checkbox for %s found.", filterCheckBox));
        } catch (Exception e) {
            // takeScreenshot("src/main/resources/screenshots/error.png");
            System.out.println(String.format("Filter checkbox for %s not found.", filterCheckBox));
            System.out.println(e.getMessage());
        }
    }
}
