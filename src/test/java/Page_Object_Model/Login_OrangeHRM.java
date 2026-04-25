package Page_Object_Model;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import junit.framework.Assert;

public class Login_OrangeHRM {

public WebDriver driver;
	
@BeforeTest
public void setup()
{
	System.out.println("Before Test executed");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
}

@Test
void TestLogin() throws InterruptedException
{
	POM_LoginPage lp = new POM_LoginPage(driver);
	Thread.sleep(5000);
	
	lp.setUserName("Admin");
	Thread.sleep(5000);
	
	lp.setPassword("admin123");
	Thread.sleep(5000);
	
	lp.ClickLogin();
	Thread.sleep(5000);
	
	Assert.assertEquals(driver.getTitle(),"OrangeHRM");
			
}

@AfterTest
public void tearDown() throws InterruptedException
{
	Thread.sleep(5000);
	driver.close();
	driver.quit();
}
}

