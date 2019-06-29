import com.madgag.gif.fmsware.AnimatedGifEncoder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class GifDriver {
    static AnimatedGifEncoder e = new AnimatedGifEncoder();

    public static void setUpGifEncoder() {
        Properties props = getPropertiesFile();
        e = new AnimatedGifEncoder();
        UUID random = UUID.randomUUID();
        String gifDirectory = "src/main/resources/gifs/";
        e.start(gifDirectory + "gif-" + random + ".gif");
        e.setDelay(Integer.parseInt(props.getProperty("gif.delay")));
        e.setRepeat(Integer.parseInt(props.getProperty("gif.repeat")));
        e.setQuality(1); //highest
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
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File shotFile = screenshot.getScreenshotAs(OutputType.FILE);
        return shotFile;
    }
}
