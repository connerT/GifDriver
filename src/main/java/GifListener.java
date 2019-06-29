import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class GifListener implements WebDriverEventListener {

    public GifListener() {
        GifDriver.setUpGifEncoder();
    }

    public void beforeAlertAccept(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterAlertAccept(WebDriver driver) {
        GifDriver.addFrame(driver);

    }

    public void afterAlertDismiss(WebDriver driver) {

    }

    public void beforeAlertDismiss(WebDriver driver) {

    }

    public void beforeNavigateTo(String url, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeNavigateBack(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterNavigateBack(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeNavigateForward(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterNavigateForward(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeNavigateRefresh(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterNavigateRefresh(WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        GifDriver.addFrame(driver);
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        GifDriver.addFrame(driver);
    }

    public void beforeScript(String script, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterScript(String script, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    public void onException(Throwable throwable, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    public void beforeGetText(WebElement element, WebDriver driver) {
        GifDriver.addFrame(driver);
    }

    public void afterGetText(WebElement element, WebDriver driver, String text) {
        GifDriver.addFrame(driver);
    }
}
