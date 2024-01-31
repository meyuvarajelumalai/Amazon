package pages;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Listeners.ITestListerClass;
import Utilities.Base_class;

public class LoginPage extends Base_class {

	public WebDriver driver;
	// private static final Logger LOGGER =
	// Logger.getLogger(LoginPage.class.getName());

	@FindBy(id = "nav-link-accountList")
	private WebElement signinbtn;

	@FindBy(xpath = "//input[@id='ap_email']")
	private WebElement Emailid;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement Continuebtn;

	@FindBy(xpath = "//input[@id='ap_password']")
	private WebElement Password;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	private WebElement signinbtn1;

	@FindBy(xpath = "//span[@class='a-list-item']")
	private WebElement EmailErrorr;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void startPortal() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		signinbtn.click();
		Emailid.sendKeys("rvedha19@gmail.com");
		Continuebtn.click();
		Password.sendKeys("Rvedha@12");
		signinbtn1.click();
	}
	
	
	public boolean validLogin(ExtentTest extenttest) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://www.amazon.in/");
			signinbtn.click();
			Emailid.sendKeys("rvedha19@gmail.com");
			Continuebtn.click();
			Password.sendKeys("Rvedha@12");
			signinbtn1.click();
			String title = driver.getTitle();
			Assert.assertEquals(title,
					"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");

			extenttest.log(Status.PASS, "Login successful");

		} catch (AssertionError e) {

			// LOGGER.log(Level.SEVERE, "Error during login: " + e.getMessage(), e);

			// LOGGER.log(Level.SEVERE, "Assertion error during login: " + e.getMessage(),
			// e);
			extenttest.log(Status.FAIL, "LOGIN FAILED: " + e.getMessage());
			return false;

			// extenttest.log(Status.FAIL, "Login failed: " + e.getMessage());
		}
		return true;

	}

	public void InvalidMailid() {

		driver.get("https://www.amazon.in/");
		signinbtn.click();
		Emailid.sendKeys("rvedh@gmail.com");
		Continuebtn.click();
		// String EmailErrorr =
		// driver.findElement(By.xpath("//span[@class='a-list-item']")).getText();
		Assert.assertEquals(EmailErrorr.getText(), "We cannot find an account with that email address");
	}

	public void validEmailidAndInvalidPassword() {
		driver.get("https://www.amazon.in/");
		signinbtn.click();
		Emailid.sendKeys("rvedha19@gmail.com");
		Continuebtn.click();
		Password.sendKeys("Rvedha");
		signinbtn1.click();
		String PasswordError = driver.findElement(By.xpath("//span[@class='a-list-item']")).getText();
		System.out.println(PasswordError);
		Assert.assertEquals(PasswordError, "Your password is incorrect");
	}

	public void EmailidWithEmptyText() {
		driver.get("https://www.amazon.in/");
		signinbtn.click();
		Emailid.sendKeys("");
		Continuebtn.click();
		String EmptyTextError = driver
				.findElement(By.xpath("//div[contains(text(),'Enter your email or mobile phone number')]")).getText();
		Assert.assertEquals(EmptyTextError, "Enter your email or mobile phone number");
	}

	public void EmailWithEmptyPassword() {
		driver.get("https://www.amazon.in/");
		signinbtn.click();
		Emailid.sendKeys("rvedha19@gmail.com");
		Continuebtn.click();
		Password.sendKeys("");
		signinbtn1.click();
		String EmptyPasswordError = driver.findElement(By.xpath("//div[contains(text(),'Enter your password')]"))
				.getText();
		System.out.println();
		Assert.assertEquals(EmptyPasswordError, "Enter your password");
	}

}
