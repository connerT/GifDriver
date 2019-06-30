import com.madgag.gif.fmsware.AnimatedGifEncoder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class GifDriver {

    static AnimatedGifEncoder e = new AnimatedGifEncoder();
    static int screenshotCounter = 0;

    public static void setUpGifEncoder(String testName) {
        Properties props = getPropertiesFile();
        e = new AnimatedGifEncoder();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String gifDirectory = "src/main/resources/gifs/";
        e.start(gifDirectory + testName + "-gif-" + timeStamp + ".gif");
        e.setDelay(Integer.parseInt(props.getProperty("gif.delay")));
        e.setRepeat(Integer.parseInt(props.getProperty("gif.repeat")));
        e.setQuality(Integer.parseInt(props.getProperty("gif.quality")));
    }

    private static Properties getPropertiesFile() {
        Properties props = new Properties();
        try {
            String resourceName = "gifdriver.properties";
            ClassLoader loader = GifDriver.class.getClassLoader();
            props.load(loader.getResourceAsStream(resourceName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }

    public static void stopGifEncoder() {
        e.finish();
    }

    public static void addFrame(WebDriver driver) {
        File screenshot = takeScreenshot(driver);
        try {
            e.addFrame(ImageIO.read(screenshot));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static File takeScreenshot(WebDriver driver) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File shotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("src/main/resources/screenshots/" + screenshotCounter + "-test-" + timeStamp + ".png");
        try {
            FileUtils.copyFile(shotFile, destFile);
            screenshotCounter++;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return shotFile;
    }

    public static void createGif() {
        File directory = new File("src/main/resources/screenshots/");
        String[] entries = directory.list();
        for (String s : entries) {
            File currentFile = new File(directory.getPath(), s);
            try {
                e.addFrame(ImageIO.read(currentFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
