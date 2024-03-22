package pageobjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageCommon extends PageUtilities {

	private WebDriver driver;
	protected Actions a;
	private JavascriptExecutor js;
	
	public PageCommon (WebDriver driver) {
		super(driver);
		this.a = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Header locators
	@FindBy(css="img[alt*='Website']")
	private WebElement automationExercise;
	
	@FindBy(css="a[href*='/products']")
	private WebElement products;
	
	@FindBy(linkText="Cart")
	private WebElement cart;
	
	@FindBy(css="a[href*='login']")
	WebElement signupLink;

	@FindBy(xpath="//i[@class='fa fa-user']/parent::a")
	private WebElement loggedInAs;
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(linkText="Delete Account")
	private WebElement deleteAccount;
	
	@FindBy(xpath="//ul/li/a[contains(@href,'api')]") //ul li a[href*='api'] //ul/li/a[contains(@href,'api')]
	private WebElement apiLink;
	
	@FindBy(css="a[href*='test_cases']")
	private WebElement testCases;
	
	@FindBy(xpath="//a[contains(@href,'youtube')]") 
	private WebElement videoTutorials;
	
	
	@FindBy(linkText="Contact us")
	private WebElement contactUs;
	
	//Footer locators
	
	@FindBy(css=".footer-bottom")
	private WebElement footer;
	
	@FindBy(css="#footer div h2")
	private WebElement subscriptionText;
	
	@FindBy(id="susbscribe_email")
	private WebElement emailEditBox;
	
	@FindBy(id="subscribe")
	private WebElement subscribeBtn;
	
	@FindBy(css=".alert-success.alert")
	private WebElement subSuccessAlert;
	
	@FindBy(id="scrollUp")
	private WebElement scrollUpArrow;
	
	//Header methods
	
	public HomePage goToHomePageFromLogo() {
		automationExercise.click();
		return new HomePage(driver);
	}
	
	public ProductsPage goToProducts() {
		a.moveToElement(products).doubleClick().build().perform();
		return new ProductsPage(driver);
	}
	
	public CartPage goToCart() {
		a.moveToElement(cart).doubleClick().build().perform();
		return new CartPage(driver);
	}
	
	public SignUpPage Logout() {
		logout.click();
		return new SignUpPage(driver);
	}
	
	public SignUpPage goToSignUp() {
		signupLink.click();
		return new SignUpPage(driver);
	}
	
	public APIPage goToAPITesting() throws InterruptedException {
		apiLink.click();
		
		if (driver.getCurrentUrl().contains("google")) {
			Thread.sleep(4000);
			apiLink.click();
		}
		
		return new APIPage(driver);
	}
	
	public TestCasesPage goToTestCases() throws InterruptedException {
		a.moveToElement(testCases).doubleClick().build().perform();
		
		if (driver.getCurrentUrl().contains("google")) {
			Thread.sleep(4000);
			a.moveToElement(testCases).doubleClick().build().perform();
		}
		
		return new TestCasesPage(driver);
	}
	
	public DeleteAccountPage deleteAccount() {
		deleteAccount.click();
		return new DeleteAccountPage(driver);
	}
	
	public String getLoggedInAsText() {
		return loggedInAs.getText();
	}
	
	public YouTubePage goToYouTubePage() {
		Actions a = new Actions(driver);
		a.moveToElement(videoTutorials).keyDown(Keys.CONTROL).click().build().perform();
		
		return new YouTubePage(driver);
	}
	
	public ContactPage goToContactUs() {
		contactUs.click();
		return new ContactPage(driver);
	}
	
	//Footer Methods
	
	public String getSubscriptionText() {
		return subscriptionText.getText();
	}
	
	public void enterSubscribeEmail(String email) {
		emailEditBox.sendKeys(email);
	}
	
	public void submitSubscription() {
		subscribeBtn.click();
	}
	
	public WebElement getSubcribeButtonEle() {
		return subscribeBtn;
	}
	
	public String getSuccessAlert() {
		return subSuccessAlert.getText();
	}
	
	public void scrollToTop() {
		js.executeScript("arguments[0].scrollIntoView()", automationExercise);
	}
	
	public void scrollToFooter() {
		js.executeScript("arguments[0].scrollIntoView()", footer);
	}
	
	public void selectScrollUpArrow() {
		scrollUpArrow.click();
	}
	
}
