package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {

	private static final String productName = "ball";

	public WebDriver driver;

	@FindBy(xpath = "//a[@id='nav-cart']")
	private WebElement cartIcon;

	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
	private WebElement ProceedToBuyBtn;

//	@FindBy(xpath = "//span[contains(text(), '" + productName
//			+ "')/ancestor::div[@data-asin]//input[@type='text']//preceding-sibling::span")
//		private WebElement quantityInput;
	
	
	
	@FindBy(xpath = "//span[@id='nav-cart-count']")
	private WebElement cartqty;
	

//pan[contains(text(), '" + productName + "')]
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void updateItemQuantity(String productName, String quantity) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();

		// click on cart icon
		js.executeScript("arguments[0].click();", cartIcon);
		// cartIcon.click();
		// Update the quantity

		driver.findElement(By.xpath("//span[contains(text(), '" + productName
				+ "')]//ancestor::div[@data-asin]//input[@type='text']//preceding-sibling::span")).click();
		;
		// System.out.println(text);
		WebElement QtyInput = driver.findElement(By.xpath("//a[@id='quantity_10']"));

		wait.until(ExpectedConditions.elementToBeClickable(QtyInput));
		QtyInput.click();
		WebElement qty = driver.findElement(By.xpath("(//input[@name='quantityBox'])[1]"));
		qty.sendKeys(Keys.DELETE);
		qty.sendKeys(quantity);
		qty.sendKeys(Keys.ENTER);
		ProceedToBuyBtn.click();

	}
	//Validate cart quntity
	public void Validatecartquntity(String quntity) {
		
		
		
		Assert.assertEquals(quntity,cartqty.getText());
		
	}

}
