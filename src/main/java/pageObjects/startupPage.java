package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class startupPage {

	public WebDriver driver = null;
	
	public startupPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By signIn = By.cssSelector("a[href*='sign_in']");
	By title = By.xpath("//h2[contains(text(),'Featured Courses')]");
	By navigationBar = By.cssSelector("div[class='nav-outer clearfix']");
	By courses = By.xpath("//div[@class='row clearfix']/div");
	
	public WebElement getLogin() {
		return driver.findElement(signIn);
	}

	public WebElement getTitle() {
		return driver.findElement(title);
	}

	public WebElement getNavigationBar() {
		return driver.findElement(navigationBar);
	}
	
	public Integer getCoursesCount() {
		return driver.findElements(courses).size();
	}

}
