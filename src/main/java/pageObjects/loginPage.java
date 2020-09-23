package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {
	
public WebDriver driver = null;
	
	public loginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By email = By.cssSelector("input[id='user_email']");
	By password = By.xpath("//input[@name='user[password]']");
	By submit = By.cssSelector("input[name='commit']");
	By alert = By.cssSelector("div[class*='alert-danger']");
	By forgotPassword = By.cssSelector("a[href*='password']");

	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getSubmitButton() {
		return driver.findElement(submit);
	}

	public WebElement captureAlert() {
		return driver.findElement(alert);
	}
	
	public WebElement getForgotPassword() {
		return driver.findElement(forgotPassword);
	}

}
