// This class interfaces with Selenium's TakesScreenshot class, and that class's getScreenshotAs function

package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Screenshots {

    private final WebDriver driver;

    // CONSTRUCTOR

    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    // PUBLIC CLASS METHODS

    public void takeScreenshot(String filePath, boolean includeTimestamp) {
        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // The screenshot file needs to be moved (or copied), because it's just a temporary file
        // (which is deleted when the JVM exits).
        // See https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/OutputType.html
        System.out.println("Screenshot temporarily saved as: " + imageFile.getAbsolutePath());

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);

        String destFile = includeTimestamp ? filePath.replace(".png", String.format("_%s.png", strDate)) : filePath;

        try {
            FileUtils.moveFile(imageFile, new File(destFile));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}