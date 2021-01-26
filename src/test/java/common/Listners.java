package common;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listners  implements ITestListener {
	
	ExtentReports extent=ReportNG.extentReportGenerator();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().pass(result.getMethod().getMethodName()+" is passed");
		
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getMethod().getMethodName()+" is failed");
		extentTest.get().fail(result.getThrowable());
		try {
			extentTest.get().addScreenCaptureFromPath(System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName()+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	
	
}
