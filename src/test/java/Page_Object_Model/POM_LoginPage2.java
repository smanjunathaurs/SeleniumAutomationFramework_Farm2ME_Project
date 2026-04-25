package Page_Object_Model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class POM_LoginPage2 {
	
	WebDriver Driver;
	
	
	//Constructors (initiate Driver)
	
	 POM_LoginPage2(WebDriver Driver)
	 {
		 this.Driver = Driver;
		 PageFactory.initElements(Driver, this); //MANDATORY
	 }
	 
	//Locators 
	 @FindBy(xpath = "//input[@placeholder='Username']") WebElement txt_username1;
	 
	 @FindBy(xpath = "//input[@placeholder='Password']") WebElement txt_password;
	 
	 @FindBy(xpath = "//button[@type='submit']") WebElement btn_login;
	 
	
	 // Action Methods	
	 public void setUserName(String username)
	 {
		 txt_username1.sendKeys(username);
	 }
	 
	 public void setPassword(String Password)
	 {
		 txt_password.sendKeys(Password);
	 }
	 public void ClickLogin()
	 {
		 btn_login.click();

	 }
	 
}
