package Farm2Me_User;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Farm2Me_User.ListenerClassDemo.class)
public class LoginTest extends BaseTest {  

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws Exception {

		String path = "src/test/resources/testdata.xlsx";
		return ExcelUtil.getTestData(path, "LoginData");
	}

	//********************************************************************************************************************************
	@Test(priority = 2, dataProvider = "loginData")
	public void loginDataDrivenTest(String username, String password, String expectedResult) throws InterruptedException {

		driver.manage().deleteAllCookies();   // 🔥 ADD
		driver.get("https://farm2me-dev.azurewebsites.net/login");

		POM_LoginPage loginPage = new POM_LoginPage(driver);

		loginPage.enterUsername(username);
		Thread.sleep(2000);
		loginPage.enterPassword(password);
		Thread.sleep(2000);
		loginPage.clickLogin();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			if (expectedResult.equalsIgnoreCase("Invalid")) {

				Thread.sleep(2000);  // wait for error

				boolean isErrorDisplayed = loginPage.isErrorDisplayed();

				Assert.assertTrue(isErrorDisplayed, "Expected error not displayed");

				System.out.println("Invalid login handled correctly");

			} else {

				wait.until(ExpectedConditions.titleContains("Farm2Me"));

				Assert.assertEquals(driver.getTitle(), "Farm2Me");

				System.out.println("Valid login success");

			}

		} catch (Exception e) {
			Assert.fail("Test failed: " + e.getMessage());
		}
	}
	//********************************************************************************************************************************

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

		System.out.println("All fields are present");
	}

	//********************************************************************************************************************************
	@Test(priority = 3)
	public void testLoginWithBlankFields() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://farm2me-dev.azurewebsites.net/login");

		POM_LoginPage loginPage = new POM_LoginPage(driver);

		// Leave fields empty and click login
		loginPage.clickLogin();

		Thread.sleep(5000); // wait for validation

		// Validate error (same logic already used)
		boolean isStillOnLoginPage = driver.getCurrentUrl().contains("login");

		Assert.assertTrue(isStillOnLoginPage, "Login should not proceed with blank fields");
		System.out.println("Blank fields validation working correctly");
	}
	//********************************************************************************************************************************
	@Test(priority = 4)
	public void testLoginWithOnlyPassword() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://farm2me-dev.azurewebsites.net/login");

		POM_LoginPage loginPage = new POM_LoginPage(driver);

		// Leave username blank
		loginPage.enterPassword("Manjuurs@123");

		loginPage.clickLogin();

		Thread.sleep(2000);

		boolean isStillOnLoginPage = driver.getCurrentUrl().contains("login");

		Assert.assertTrue(isStillOnLoginPage, 
				"Login should not proceed when username is blank");

		System.out.println("Username blank validation working correctly");
	}
	//*******************************************************************************************************************************
	@Test(priority = 5)
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
			System.out.println("Error message displayed");
		}

		// simple clear + enter valid password -  clear and enter valid password

		if (loginPage.waitForErrorMessage()) {
			loginPage.clearAndEnterValidPassword("Manjuurs@123");
		}

		loginPage.clickLogin();
		Thread.sleep(5000);

		loginPage.closePopupIfPresent();
		Thread.sleep(5000);

		//Validate Title should be Farm2Me

		driver.getTitle();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.titleContains("Farm2Me"));

		Assert.assertEquals(driver.getTitle(), "Farm2Me");  //  ADD HERE
		Thread.sleep(3000);
		System.out.println("Navigate to HomePage Sucessfully");

	}
}

