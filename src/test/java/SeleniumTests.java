import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Listeners({ GifTestListener.class })
public class SeleniumTests {

    public EventFiringWebDriver driver;

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
        WebDriver chDriver = new ChromeDriver(options);
        chDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver = new EventFiringWebDriver(chDriver);
        driver.register(new GifListener());
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        driver.get("https://reddit.com");

        WebElement search = driver.findElement(By.id("header-search-bar"));
        search.sendKeys("Selenium Grid");

        search.sendKeys(Keys.RETURN);
        WebElement firstPost = driver.findElement(By.cssSelector("#t3_blg4ll"));

        firstPost.click();

        driver.findElement(By.linkText("POSTS")).click();

        System.out.println("Stop");
    }

    @Test
    public void SecondTest() {
        driver.get("https://reddit.com");

        WebElement search = driver.findElement(By.id("header-search-bar"));
        search.sendKeys("WebDriver");

        search.sendKeys(Keys.RETURN);
        driver.findElement(By.className("s1vwhtdg-4")).click();

        driver.findElement(By.className("s11jmi9t-3")).click();
    }
}
