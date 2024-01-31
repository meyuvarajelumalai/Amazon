package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Listeners.ITestListerClass;
import Utilities.Base_class;
import Utilities.ExtendReport;
import pages.LoginPage;



@Listeners(ITestListerClass.class)
public class LoginTest extends Base_class {

	public static WebDriver driver;

	@BeforeTest
	public static void setUp() {
		driver = Base_class.driverInitialization("chrome");

	}

	@Test
	public void validLoginTest() {
		ExtendReport.extenttest =extentReports.createTest("LoginTest"+ " : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Valid Emailid and Valid Password");
		LoginPage loginpage = new LoginPage(driver);
		
		
		
		Assert.assertTrue(loginpage.validLogin(ExtendReport.extenttest));

	}
	
	@Test
	public void validEmailidAndInvalidPasswordTest() {
		ExtendReport.extenttest =extentReports.createTest("LoginTest"+ " : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Valid Email id And Invalid Password");
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.validEmailidAndInvalidPassword();
	}
	@Test
	public void InvalidMailidTest() {
		ExtendReport.extenttest =extentReports.createTest("LoginTest"+ " : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Invalid Mail id ");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.InvalidMailid();
	}
	@Test
	public void EmailidWithEmptyTextText() {
		ExtendReport.extenttest =extentReports.createTest("LoginTest"+ " : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Emailid With Empty Text");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.EmailidWithEmptyText();
	}
	@Test
	public void EmailWithEmptyPasswordTest() {
		ExtendReport.extenttest =extentReports.createTest("LoginTest"+ " : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Email With Empty Password");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.EmailWithEmptyPassword();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
	@BeforeSuite
	public void extentReportSetUp(){
		
		extentReportStart();
	}
	
	@AfterSuite
	public void extentReportTeardown() throws IOException {
		extentReportTearDown();
	}
	
}
