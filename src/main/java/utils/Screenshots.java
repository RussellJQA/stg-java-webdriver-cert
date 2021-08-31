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

    // CONSTRUCTOR

    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    // PUBLIC CLASS METHODS

    public void takeScreenshot(String filepath) {
        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // The screenshot file needs to be moved (or copied), because it's just a temporary file
        // (which is deleted when the JVM exits).
        // See https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/OutputType.html
        System.out.println("Screenshot temporarily saved as: " + imageFile.getAbsolutePath());

        try {
            FileUtils.moveFile(imageFile, new File(filepath));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}