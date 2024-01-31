package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base_class {

	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentSparkReporter sparkReporter;
	public static File file;

	public static WebDriver driverInitialization(String browsername) {
		switch (browsername.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "safari":
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		return driver;
	}

	public static void sendKey(By name, String text) {
		driver.findElement(name).sendKeys(text);
	}

	public static void click(By name) {
		driver.findElement(name);
	}

	public void extentReportStart() {
		extentReports = new ExtentReports();

		file = new File("D:\\C drive\\Yui\\eclipse-workspace\\Amazon\\Reports");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		// org.openqa.selenium.Capabilities capabilities
		// =((RemoteWebDriver)driver).getCapabilities(); //
		// extent.setSystemInfo("Browser",
		// capabilities.getBrowserName()+capabilities.getBrowserVersion());
		extentReports.setSystemInfo("URL", "https://auth.dev.identify.crowncommercial.gov.uk");
		extentReports.setSystemInfo("User Name", "vijay@yopmail.com");
		extentReports.setSystemInfo("User Password", "vijay1234");
	}

	public void extentReportTearDown() throws IOException {
		extentReports.flush();
		file = new File("D:\\C drive\\Yui\\eclipse-workspace\\Amazon\\Reports\\Index.html");
		Desktop.getDesktop().browse((file).toURI());

	}

	public String takeScreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File scrfile = screenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File("Screenshort\\.png" + "_" + timeStamp + ".png");
		FileUtils.copyFile(scrfile, destfile);
		return destfile.getAbsolutePath();
	}

}
