package Academy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.base;

public class Listeners extends base implements ITestListener {

	ExtentReports reports = ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	/*
	 * When we run in sequential run ExtentReport will be working fluently but when we run it 
	 * in parallel execution in Listeners all the test will come at the same time so it will 
	 * create a confusion which test has reported an error and which test has passed
	 * so to avoid this kind of problems we need to use ThreadLocal
	 * ThreadLocal will maintain the object and instances thread safe as the test method
	 * of ExtentTest class is same for all for that reason we need to use ThreadLocal
	 * Syntax ThreadLocal<ExtentTest> reportsTest = new ThreadLocal();
	 */
	ThreadLocal<ExtentTest> reportsTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		test = reports.createTest(result.getMethod().getMethodName());
		reportsTest.set(test);
	}
 
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		reportsTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// result handles which method is curently into execution and what is the status of the 
		// methods and getThrowable gives you the log of the error which is thrown by this method
		reportsTest.get().fail(result.getThrowable());
		
		WebDriver driver=null;		//local driver object
		// get the method name which is failed
		String failedMethodName = result.getMethod().getMethodName();
		//to get the driver from which the testcase got failed and pass to the getScreenShot method
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// code to capture screenshot
		try {
			// get the screenshot in the reports
			// arguments filepath in string & testcase name
			//getScreenShot method is used to obtain the screenshot and store the .png file locally 
			// and returns the file path in string format
			reportsTest.get().addScreenCaptureFromPath(getScreenShot(failedMethodName, driver), failedMethodName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush();
	}
	
	

}
