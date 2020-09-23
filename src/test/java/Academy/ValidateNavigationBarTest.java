package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.startupPage;
import resources.base;

public class ValidateNavigationBarTest extends base{
	// making a copy locally so when the test is running in parallely it is not overriden by another driver
	private static Logger log = LogManager.getLogger(ValidateNavigationBarTest.class.getName());
	public WebDriver driver; 
		
	@BeforeMethod
	public void initalizeTest() throws IOException {
		driver = initializeDriver();
		String URL = externalData.getProperty("url");
		driver.get(URL);
	}
	
	@Test
	public void validateNavigationBar() {
		startupPage stPage = new startupPage(driver);
		Assert.assertTrue(stPage.getNavigationBar().isDisplayed());
		log.info("Successfull validate navigation bar");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
