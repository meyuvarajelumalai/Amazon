package Listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

import Utilities.Base_class;
import Utilities.ExtendReport;

public class ITestListerClass extends Base_class implements ITestListener{
	
	
	public String testName;
	public String methodName;
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
		try {
			
			ExtendReport.extenttest.pass(result.getMethod().getMethodName() + " : " + "Test pass ",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		
		
		try {
			ExtendReport.extenttest.fail(result.getMethod().getMethodName() + " : " + "Test Fail ",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	@Override
	public void onStart(ITestContext context) {
		
	}
	@Override
	public void onFinish(ITestContext context) {
		
	}
	
	

	

}
