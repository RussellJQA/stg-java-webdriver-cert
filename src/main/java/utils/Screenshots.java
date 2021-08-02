// This class interfaces with Selenium's TakesScreenshot class, and that class's getScreenshotAs function

package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshots {

    private final WebDriver driver;

    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String filepath) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File image = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(filepath);

        // The screenshot file will need to be moved, because it's just a temporary file
        // (which is deleted when the JVM exits).
        // See https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/OutputType.html
        System.out.println("Screenshot temporarily saved to: " + image.getAbsolutePath());

        try {
            FileUtils.moveFile(image, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}