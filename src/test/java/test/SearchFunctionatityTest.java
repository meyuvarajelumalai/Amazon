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

import Listeners.ITestListerClass;
import Utilities.Base_class;
import Utilities.ExtendReport;
import pages.LoginPage;
import pages.SearchPage;

//@Listeners(ITestListerClass.class)
public class SearchFunctionatityTest extends Base_class {
	public static WebDriver driver;

	@BeforeTest
	public static void setUp() {

		driver = Base_class.driverInitialization("chrome");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.startPortal();
	}

	
	@Test
	public void SearchFunctionalityTest() throws IOException, InterruptedException {
		ExtendReport.extenttest =extentReports.createTest("Search Functionatity"+ " : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Validate Search Functinality");
		SearchPage search = new SearchPage(driver);

		search.ValidateSearchFunctionality(ExtendReport.extenttest);

	}
//	@AfterTest
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
