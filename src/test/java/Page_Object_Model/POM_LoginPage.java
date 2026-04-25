package Page_Object_Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class POM_LoginPage {
	
	WebDriver Driver;
	
	
	//Constructors (initiate Driver)
	
	 POM_LoginPage(WebDriver Driver)
	 {
		 this.Driver = Driver;
	 }
	
	//Locators 
	 By txt_username_locators = By.xpath("//input[@placeholder='Username']");
	 By txt_password_locators = By.xpath("//input[@placeholder='Password']");
	 By btn_login_locators = By.xpath("//button[@type='submit']");
	
	 // Action Methods	
	 public void setUserName(String username)
	 {
		 Driver.findElement(txt_username_locators).sendKeys(username);
	 }
	 
	 public void setPassword(String Password)
	 {
		 Driver.findElement(txt_password_locators).sendKeys(Password);
	 }
	 
	 public void ClickLogin()
	 {
		 Driver.findElement(btn_login_locators).click();
	 }
	 

}
