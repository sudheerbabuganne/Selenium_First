package common;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportNG {
	
	public static ExtentReports extent;
	
	public static ExtentReports extentReportGenerator()
	{
		String path=System.getProperty("user.dir")+File.separator+"reports"+File.separator+"AutomationReport.html";
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(path);
		reporter.config().setReportName("MyAutomation Report");
		reporter.config().setDocumentTitle("Selenium -Report");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sudheer");
		return extent;
	}
}
