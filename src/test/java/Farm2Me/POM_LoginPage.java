package Farm2Me;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class POM_LoginPage {
	WebDriver driver;

	// Locate elements

	@FindBy(xpath = "//input[@id='username']") private WebElement usernameField;
	@FindBy(xpath = "//input[@id='password']") private WebElement passwordField;

	@FindBy(xpath = "//*[contains(text(),'Invalid Password')]") private WebElement errorMessage;
	@FindBy(xpath = "//*[contains(text(),'Invalid') or contains(text(),'not') or contains(text(),'incorrect')]")
	private List<WebElement> errorMessages;
	@FindBy(xpath = "//div[@class='p-checkbox-box']") private WebElement RememberMeCheckbox;
	@FindBy(xpath = "//div[@class='forgot-password-link cursor-pointer']") private WebElement ForgotPasswordlink;
	@FindBy(xpath = "//span[@class='primary-text-color']") private WebElement Signuplink;
	@FindBy(xpath = "//button[contains(.,'Sign')]") private WebElement loginButton;
	@FindBy(xpath = "button[aria-label='Close']") private WebElement Homepage;


	// Constructor
	public POM_LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// validations
	public boolean isusernameField() { return usernameField.isDisplayed(); }
	public boolean ispasswordFieldDisplayed() { return passwordField.isDisplayed(); }
	public boolean isRememberMeCheckboxDisplayed() { return RememberMeCheckbox.isDisplayed(); }
	public boolean isForgotPasswordlinkDisplayed() { return ForgotPasswordlink.isDisplayed(); }
	public boolean isSignuplink() { return Signuplink.isDisplayed(); }
	public boolean isloginButtonDisplayed() { return loginButton.isDisplayed(); }
	public boolean isHomepageDisplayed() { return Homepage.isDisplayed(); }


	// Page actions
	public void enterUsername(String username) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement user = wait.until(ExpectedConditions.visibilityOf(usernameField));

		user.clear();
		user.sendKeys(username);
	}


	public void enterPassword(String password) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement pass = wait.until(ExpectedConditions.visibilityOf(passwordField));

		pass.clear();
		pass.sendKeys(password);
	}



	public void RememberMeCheckbox() {
		RememberMeCheckbox.isDisplayed();
	}

	public void ForgotPasswordlink() {
		ForgotPasswordlink.isDisplayed();
	}

	public void Signuplink() {
		Signuplink.isDisplayed();
	}

	public void loginButton() {
		loginButton.isDisplayed();
	}

	public void clickLogin() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		btn.click();
	}

	public boolean waitForErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
	}

	public boolean isErrorMessageDisplayed() {
		return errorMessage.isDisplayed();
	}

	public boolean isErrorDisplayed() {
		return errorMessages.size() > 0;
	}

	public String getErrorMessageText() {
		return errorMessage.getText();
	}

	// simple clear + enter valid password
	public void clearAndEnterValidPassword(String pwd) {
		passwordField.sendKeys(Keys.CONTROL + "a");
		passwordField.sendKeys(Keys.DELETE);
		passwordField.sendKeys(pwd);
	}

	public void closePopupIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(Homepage));

			if (Homepage.isDisplayed()) {
				Homepage.click();
				System.out.println("Popup not present, skipping");
			}
		} catch (Exception e) {
			System.out.println("Popup closed");
		}
	}


}
