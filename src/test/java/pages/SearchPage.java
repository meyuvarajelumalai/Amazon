package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utilities.Base_class;
import Utilities.ExtendReport;

public class SearchPage extends Base_class {

	public WebDriver driver;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement Searchbox;

	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	private WebElement Searchbtn;

	@FindBy(xpath = "//div[@data-cy='title-recipe']//following-sibling::h2")
	private List<WebElement> SearchResult;

	@FindBy(id = "add-to-cart-button")
	private WebElement addCardBtn;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	// div[@data-cy="title-recipe"]
	// //div[@data-cy='title-recipe']//following-sibling::h2

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void SearchProduct(String productname) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Searchbox.sendKeys(productname);
		Searchbtn.click();
	}

	public void addProductToCart_1(String productName) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		for (WebElement result : SearchResult) {

			if (result.getText().toLowerCase().contains("shoe")) {

				System.out.println(result.getText());
				System.out.println("Before Click");
				//wait.until(ExpectedConditions.elementToBeClickable(result)).click();
				driver.findElement(By.xpath("//div[@data-cy='title-recipe']//following-sibling::h2//a")).click();
				System.out.println("After Click");
				break;

			}

		}
		Set<String> windowHandles = driver.getWindowHandles();
		String newTabHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
		driver.switchTo().window(newTabHandle);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(addCardBtn)).click();
	}

	public void addProductToCart(String productName) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// for (WebElement result : SearchResult) {

		// wait.until(ExpectedConditions.visibilityOf(result));
		// System.out.println(result.getText());
//	result.click();

		WebElement result = driver.findElement(By.xpath(
				"//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//div[@class='a-section aok-relative s-image-tall-aspect']"));
		wait.until(ExpectedConditions.elementToBeClickable(result)).click();

		// wait.until(ExpectedConditions.elementToBeClickable(result)).click();
		// Get the handles of all currently open windows/tabs
		Set<String> windowHandles = driver.getWindowHandles();
		// Switch to the new tab (assuming it's the last one opened)
		String newTabHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
		driver.switchTo().window(newTabHandle);
		// String text =
		// driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
		// WebElement addcart2 = driver.findElement(By.xpath("//input[@title='Add to
		// Shopping Cart']"));updateItemQuantity
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(addCardBtn)).click();

		// js.executeScript("arguments[0].click();", addcart2);
		// wait.until(ExpectedConditions.visibilityOf(addCardBtn));
		/// js. executeScript("arguments[0].scrollIntoView(true);", addCardBtn);
		// js.executeScript("arguments[0].click();", addCardBtn);
		// addCardBtn.click();
		// break;

	}

	public void ValidateSearchFunctionality(ExtentTest extenttest) throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		extenttest.log(Status.INFO, "Validate The Search Functionality With THe Key Word:Shoe ");
		// try {
		SoftAssert softAssert = new SoftAssert();

		Searchbox.sendKeys("shoe");
		long startTime = System.currentTimeMillis();
		Searchbtn.click();

		// Measure response time
		long endTime = System.currentTimeMillis();
		long responseTime = endTime - startTime;
		System.out.println("Response time: " + responseTime + " milliseconds");

		for (WebElement result : SearchResult) {

			if (result.getText().toLowerCase().contains("shoe")) {

			}

			else {
				System.err.println(result.getText().toLowerCase());
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				wait.until(ExpectedConditions.elementToBeClickable(result)).click();

				// Get the handles of all currently open windows/tabs
				Set<String> windowHandles = driver.getWindowHandles();
				// Switch to the new tab (assuming it's the last one opened)
				String newTabHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
				driver.switchTo().window(newTabHandle);
				// To get the product name

				WebElement productName = driver.findElement(By.xpath("//span[@id='productTitle']"));
				wait.until(ExpectedConditions.visibilityOf(productName));
				String text = productName.getText();
				extenttest.log(Status.FAIL,
						"Search Result Failed: " + text + " :: " + "This product Name Doesn't Have A Search Keyword ");
				extenttest.fail(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
				// Close the current tab
				driver.close();
				// Switch back to the original tab (assuming it's the first one)
				String originalTabHandle = windowHandles.toArray()[0].toString();
				driver.switchTo().window(originalTabHandle);

			}

		}

		/*
		 * Searchbox.clear(); Searchbox.sendKeys("shoe"); Searchbtn.click();
		 * 
		 * for (WebElement result : SearchResult) { System.out.println("shirt names : "
		 * + result.getText().toLowerCase()); if
		 * (result.getText().toLowerCase().contains("shoe")) {
		 * 
		 * softAssert.assertTrue(true);
		 * 
		 * }
		 * 
		 * } Searchbox.clear(); Searchbox.sendKeys("perfume"); Searchbtn.click();
		 * 
		 * for (WebElement result : SearchResult) {
		 * System.out.println("perfume names : " + result.getText().toLowerCase()); if
		 * (result.getText().toLowerCase().contains("perfume")) {
		 * 
		 * softAssert.assertTrue(true);
		 * 
		 * // Assert.assertTrue(true);
		 * 
		 * }
		 * 
		 * }
		 */
		// }

//		catch (AssertionError e) {
//
//		}
	}

}
