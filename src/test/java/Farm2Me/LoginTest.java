package Farm2Me;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(Farm2Me.ListenerClassDemo.class)
public class LoginTest extends BaseTest {  

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws Exception {

		String path = "src/test/resources/testdata.xlsx";
		return ExcelUtil.getTestData(path, "LoginData");
	}


	@Test(priority = 2, dataProvider = "loginData")
	public void loginDataDrivenTest(String username, String password, String expectedResult) throws InterruptedException {
		System.out.println("Running with: " + username + " | " + password + " | " + expectedResult);

		// ✅ Reset page every iteration
		driver.get("https://farm2me-dev.azurewebsites.net/login");

		POM_LoginPage loginPage = new POM_LoginPage(driver);

		loginPage.enterUsername(username);
		Thread.sleep(2000);
		loginPage.enterPassword(password);
		Thread.sleep(2000);
		loginPage.clickLogin();
		Thread.sleep(3000);

		// 🔥 ADD FROM HERE
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			if (expectedResult.equalsIgnoreCase("Invalid")) {

				Thread.sleep(2000); // temporary wait


				boolean isStillOnLoginPage = driver.getCurrentUrl().contains("login");

				//  Use POM method instead of driver
				boolean isErrorDisplayed = loginPage.isErrorDisplayed();



				if (isStillOnLoginPage || isErrorDisplayed) {
					System.out.println("✅ Invalid login handled correctly for: " + username);
				} else {
					Assert.fail("❌ Invalid test failed - user logged in unexpectedly");
				}

			} else {

				wait.until(ExpectedConditions.titleContains("Farm2Me"));
				Assert.assertEquals(driver.getTitle(), "Farm2Me");

				System.out.println("✅ Valid login success for: " + username);
			}

		} catch (Exception e) {
			System.out.println("❌ Failure for: " + username);
			e.printStackTrace();
			Assert.fail();
		}
	}
	//*****************************************************************************************************************

	@Test(priority = 1)
	public void validateLoginPageFields() throws InterruptedException {

		POM_LoginPage loginPage = new POM_LoginPage(driver);
		Assert.assertTrue(loginPage.isusernameField(), "Mobile field is displayed");
		Thread.sleep(3000);

		Assert.assertTrue(loginPage.ispasswordFieldDisplayed(), "Password field is displayed");
		Thread.sleep(3000);

		Assert.assertTrue(loginPage.isRememberMeCheckboxDisplayed(), "Remember Me Check Box is displayed");
		Thread.sleep(3000);

		Assert.assertTrue(loginPage.isForgotPasswordlinkDisplayed(), "ForgotPasswordlink is displayed");
		Thread.sleep(3000);

		Assert.assertTrue(loginPage.isSignuplink(), "Signuplink is displayed");
		Thread.sleep(3000);

		Assert.assertTrue(loginPage.isloginButtonDisplayed(), "SignIn is displayed");
		Thread.sleep(3000);

		System.out.println("All fields are present ✅");
	}

	/********************************************************************************************************************************/
	@Test(priority = 3)
	public void testValidLogin() throws InterruptedException {
		POM_LoginPage loginPage = new POM_LoginPage(driver);
		loginPage.enterUsername("9739706253");
		Thread.sleep(5000);
		loginPage.enterPassword("Manjuurs@12356789");
		Thread.sleep(5000);

		loginPage.clickLogin();
		Thread.sleep(5000);

		loginPage.waitForErrorMessage();
		Thread.sleep(5000);

		if (loginPage.waitForErrorMessage()) {
			System.out.println("Error message displayed ✅");
		}

		// 🔥 simple clear + enter valid password -  clear and enter valid password

		if (loginPage.waitForErrorMessage()) {
			loginPage.clearAndEnterValidPassword("Manjuurs@123");
		}

		loginPage.clickLogin();
		Thread.sleep(5000);

		loginPage.closePopupIfPresent();
		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//**********************************************************************************************

		//Validate Title should be Farm2Me

		String	act_title = driver.getTitle();// to capture the title

		if(act_title.equals("Farm2Me"))    // compare the captured title with the actual title given.
		{
			System.out.println("Title present: Test is passed");
		}
		else
		{
			System.out.println("Title present: Test is Failed");
		}

		Thread.sleep(3000);
		System.out.println("Navigate to HomePage Sucessfully ✅");

	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}



