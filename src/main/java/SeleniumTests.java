import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumTests {

    public WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", SeleniumTests.class.getResource("chromedriver.exe").getFile());

        //Create prefs map to store all preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("-incognito");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void setupGifEncoder() {
        GifDriver.setUpGifEncoder();
    }

    @AfterMethod
    public void stopGifEncoder() {
        GifDriver.stopGifEncoder();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        driver.get("https://reddit.com");
        GifDriver.addFrame(driver);
        WebElement search = driver.findElement(By.id("header-search-bar"));
        search.sendKeys("Selenium Grid");
        GifDriver.addFrame(driver);
        search.sendKeys(Keys.RETURN);
        WebElement firstPost = driver.findElement(By.cssSelector("#t3_blg4ll"));
        GifDriver.addFrame(driver);
        firstPost.click();
        GifDriver.addFrame(driver);
        driver.findElement(By.linkText("POSTS")).click();
        GifDriver.addFrame(driver);
        System.out.println("Stop");
    }

    @Test
    public void SecondTest() {
        driver.get("https://reddit.com");
        GifDriver.addFrame(driver);
        WebElement search = driver.findElement(By.id("header-search-bar"));
        search.sendKeys("WebDriver");
        GifDriver.addFrame(driver);
        search.sendKeys(Keys.RETURN);
        driver.findElement(By.className("s1vwhtdg-4")).click();
        GifDriver.addFrame(driver);
        driver.findElement(By.className("s11jmi9t-3")).click();
        GifDriver.addFrame(driver);
    }
}
