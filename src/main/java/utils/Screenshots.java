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

/**
 * Interface with Selenium's TakesScreenshot class, and with that class's getScreenshotAs function
 */
public final class Screenshots {

    private final WebDriver driver;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * Constructor
     *
     * @param driver the instance of a Selenium WebDriver which is being used for testing
     */
    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * Takes a screenshot and stores it in the specified location, optionally adding a timestamp at the end of the filename
     *
     * @param filePath         specified location to store the screenshot (e.g., "testChallenge6.png" or "filtering_for_Model_skyline_Skyline.png")
     * @param includeTimestamp whether to add an optional timestamp at the end of the filename
     */
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