package Farm2Me;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Listeners(Farm2Me.ListenerClassDemo.class)
public class ListenerDemo {

	@Test
	public void login() throws InterruptedException
	{
		String projectpath = System.getProperty("uder.dir");
		System.out.println("C:/Users/ManjunathaUrs/eclipse-workspace/Selenium-java");

		System.setProperty("webdriver.chrome.driver", "C:/Users/ManjunathaUrs/eclipse-workspace/Selenium-java/Chrome-Driver/chromedriver.exe");
		// Launch Browser
		WebDriver driver = new ChromeDriver();

		// Open URL
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		//Maximize current window
		driver.manage().window().maximize();

		// Java wait() Method
		Thread.sleep(5000);

		//Register User Name
		driver.findElement(By.cssSelector("input[placeholder='Username']")).click();
		driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Admin");
		// Java wait() Method
		Thread.sleep(3000);

		//Required Password
		driver.findElement(By.cssSelector("input[placeholder='Password']")).click();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin123");
		Thread.sleep(3000);

		// click sign_in Button 
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(9000);

		//Menu
		driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']//span[1]")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//span[normalize-space()='Dashboard']")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']//span[1]")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//img[@alt='profile picture']")).click();
		Thread.sleep(9000);

		// Logout from the Application
		driver.findElement(By.xpath("(//a[@class='oxd-userdropdown-link'])[4]")).click();
		Thread.sleep(3000);
		
		driver.close();
		//*********************************************************************************************

		// validate TestCase Using Title 
		String act_title = driver.getTitle();
		if(act_title.equals("OrangeHRM"))
		{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
		}
	}
}
