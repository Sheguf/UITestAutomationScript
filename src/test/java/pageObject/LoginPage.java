package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.Base;

public class LoginPage extends Base {
	
	public void enterUserNameAndPassword(String user, String pass) {
	WebElement userName	= driver.findElement(By.xpath("//input[@id='user-name']"));
	userName.sendKeys("standard_user");
	
	WebElement password	= driver.findElement(By.xpath("//input[@id='password']"));
	password.sendKeys("secret_sauce");
	}
	
	public void clickOnLoginButton() {
	WebElement loginBtn = driver.findElement(By.xpath("//input[@id='login-button']"));
	loginBtn.click();
	}
    
	public boolean validateSuccessfulLogin() {
	return driver.findElement(By.xpath("//span[@class='title']")).isDisplayed();
	}
	
	public boolean validateErrorMessage() {
		return driver.findElement(By.xpath("//div[@class='error-message-container error']//button")).isDisplayed();
	}
	
}
