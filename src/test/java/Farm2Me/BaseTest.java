package Farm2Me;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.util.HashMap;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeSuite
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        // 🔥 Disable autofill + suggestions
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        // 🔥 Fresh browser profile

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://farm2me-dev.azurewebsites.net/login");

        // 🔥 Clear all data
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}