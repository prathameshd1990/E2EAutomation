package Academy;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.startupPage;
import resources.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ValidateTitleTest extends base{
	// making a copy locally so when the test is running in parallely it is not overriden by another driver
	public WebDriver driver; 
	
	private static Logger log = LogManager.getLogger(ValidateTitleTest.class.getName());
	
	@BeforeMethod
	public void initalizeTest() throws IOException {
		driver = initializeDriver();
		String URL = externalData.getProperty("url");
		driver.get(URL);
	}

	@Test
	public void validateAppTitle()  {
		startupPage stPage = new startupPage(driver);
		// compare the text from startupPage and compare if same pass the test if not fail the test
		Assert.assertEquals(stPage.getTitle().getText(), "Featured Courses");
		log.info("Application validated succesfully");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
