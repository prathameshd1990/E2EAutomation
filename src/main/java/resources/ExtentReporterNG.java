package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports reports;
	
	public static ExtentReports getReportObject() {
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		// to set the report name
		reporter.config().setReportName("E2E Automation");
		// to set the document title
		reporter.config().setDocumentTitle("Test Results");
		
		// main class to get the reports after attaching the ExtentSparkReporter object
		// to ExterntReport class
		reports = new ExtentReports();
		// now attach the reporter to the extent reports object
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Prathamesh Dhamanaskar");
		
		return reports;
	}
}
