package Farm2Me;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

	protected WebDriver driver;
	protected String baseUrl = "https://farm2me-dev.azurewebsites.net/login";

	@BeforeMethod
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "E:/Farm2Me Live Project - Automation Testing/eclipse-workspace/Farm2Me/CromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(5000);       
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.get(baseUrl);
		Thread.sleep(5000);
	}

	@AfterTest
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}

}


