package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class forgotPasswordPage {
	
	WebDriver driver=null;
	
	public forgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By forgottenEmail = By.xpath("//input[@type='email']");
	By submit = By.name("commit");
	By alert = By.cssSelector("[class='help-block']");
	
	public WebElement getForgottenEmail() {
		return driver.findElement(forgottenEmail);
	}
	
	public WebElement getSubmitnButton() {
		return driver.findElement(submit);
	}
	
	public WebElement getAlert() {
		return driver.findElement(alert);
	}

}
