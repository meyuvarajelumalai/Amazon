package Utilities;

import java.io.IOException;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;

public class ExtendReport {
	
	public static 	ExtentTest extenttest;
	
	@BeforeSuite
	public void extentReportStartUp() {
		Base_class baseclass = new Base_class();
		baseclass.extentReportStart();
	}
	

	public void extentReportEnd() throws IOException {
		Base_class baseclass = new Base_class();
		baseclass.extentReportTearDown();
	}
	
	
}
