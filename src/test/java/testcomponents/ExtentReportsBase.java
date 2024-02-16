package testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsBase {

	public static ExtentReports getReporterObject() {
		//String path = System.getProperty("user.dir") + "\\reports\\report.html";
		String path = System.getProperty("user.dir") + "\\reports\\results.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Daniel");
		
		return extent;
	}
	
}
