package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.forgotPasswordPage;
import pageObjects.loginPage;
import pageObjects.startupPage;
import resources.base;

// maven resources filtering
public class E2EMechanismTest extends base{
	// making a copy locally so when the test is running in parallely it is not overriden by another driver
	private static Logger log = LogManager.getLogger(E2EMechanismTest.class.getName());
	public WebDriver driver; 	 
	@BeforeTest
	public void initalizeTest() throws IOException {
		driver = initializeDriver();
		String URL = externalData.getProperty("url");
		driver.get(URL);
		log.info("browser launched succesfully");
	}
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String Username,String Password) throws IOException{
		String URL = externalData.getProperty("url");
		driver.get(URL);
		startupPage stPage = new startupPage(driver);
		stPage.getLogin().click();
		
		loginPage lPage = new loginPage(driver);
		lPage.getEmail().sendKeys(Username);
		lPage.getPassword().sendKeys(Password);
		lPage.getSubmitButton().click();
		log.info("User logged in with: " + Username + " " + Password);
		String alert = lPage.captureAlert().getText();
		//Assert.assertEquals(Alert, "Invalid email or password.");
		log.info("Alert message:" + alert);
		lPage.getForgotPassword().click();
		
		forgotPasswordPage fpPage = new forgotPasswordPage(driver);
		fpPage.getForgottenEmail().sendKeys(Username);
		fpPage.getSubmitnButton().click();
		Assert.assertEquals(fpPage.getAlert().getText(), "We couldn't find an account with that email address");
	}
	
	@Test(priority=2)
	public void validateCoursesCount() {
		String URL = externalData.getProperty("url");
		driver.get(URL);
		startupPage stPage = new startupPage(driver);
		int count = stPage.getCoursesCount();
		Assert.assertEquals(22, count);
		log.info("No of courses mathced i.e: " + count);
	}
	
	@DataProvider
	public Object[][] getData() {
		//2D object array
		// Rows - how many sets of value we want to send
		// column - how many parameters are there in a row
								// row column	
		Object[][] data = new Object[2][2];
		// User-1
		data[0][0] = "prathameshd1990@yahoo.in";
		data[0][1] = "Mtech123$";
		// User-2
		data[1][0] = "sagar_gaja_90@gmail.com";
		data[1][1] = "Symbios#90";
		
		return data;
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
