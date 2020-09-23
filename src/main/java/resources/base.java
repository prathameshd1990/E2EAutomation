package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {
	
	public WebDriver driver = null;
	public Properties externalData = null;
	
	public WebDriver initializeDriver() throws IOException {
		String filePath = System.getProperty("user.dir");
		externalData = new Properties();
		FileInputStream file = new FileInputStream(filePath + "\\src\\main\\java\\resources\\data.properties");
		externalData.load(file);
		String browserName = externalData.getProperty("browser");
		//String browserName = System.getProperty("browser");
		if(browserName.equals("chrome")) {			//for chrome
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")) { 	// for firefox
			System.setProperty("webdriver.firefox.driver", "C:\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("IE")) {		// for IE
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver = new InternetExplorerDriver();
		}
		
		// Defining the timeout for how many seconds the application should wait before 
		// giving timeout
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public String getScreenShot(String TestName, WebDriver driver) throws IOException {
		
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + TestName + ".png";
		FileUtils.copyFile(source,new File(destinationFile));
		
		return destinationFile;
	}

}
