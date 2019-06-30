import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class GifTestListener implements ITestListener {

    Properties props;

    public GifTestListener() {
        props = getPropertiesFile();
    }

    public void onTestStart(ITestResult result) {
        clearScreenshotDirectory();
        GifDriver.setUpGifEncoder(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        if (!Boolean.parseBoolean(props.getProperty("gif.on.failure.only"))) {
            GifDriver.createGif();
            GifDriver.stopGifEncoder();
        }
    }

    public void onTestFailure(ITestResult result) {
        GifDriver.createGif();
        GifDriver.stopGifEncoder();
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        clearScreenshotDirectory();
    }

    private void clearScreenshotDirectory() {
        File directory = new File("src/main/resources/screenshots/");
        String[] entries = directory.list();
        for (String s : entries) {
            File currentFile = new File(directory.getPath(), s);
            currentFile.delete();
        }
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
}
