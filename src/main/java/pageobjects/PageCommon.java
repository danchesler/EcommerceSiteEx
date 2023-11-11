package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCommon extends PageUtilities {

	private WebDriver driver;
	protected Actions a;
	private JavascriptExecutor js;
	
	public PageCommon (WebDriver driver)
	{
		super(driver);
		this.a = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Header locators
	
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
	
	@FindBy(css="a[href*='test_cases']")
	private WebElement testCases;
	
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
	
	//Added to cart popup
	@FindBy(css="a[href*='view'] u")
	protected WebElement viewCartPopup;
	
	@FindBy(css="button[data-dismiss='modal']")
	protected WebElement continueShopping;
	
	//Header methods
	
	public ProductsPage goToProducts()
	{
		a.moveToElement(products).doubleClick().build().perform();
		return new ProductsPage(driver);
	}
	
	public CartPage goToCart()
	{
		a.moveToElement(cart).doubleClick().build().perform();
		return new CartPage(driver);
	}
	
	public SignUpPage Logout()
	{
		logout.click();
		return new SignUpPage(driver);
	}
	
	public SignUpPage goToSignUp()
	{
		signupLink.click();
		return new SignUpPage(driver);
	}
	
	public TestCasesPage goToTestCases()
	{
		a.moveToElement(testCases).doubleClick().build().perform();
		return new TestCasesPage(driver);
	}
	
	
	public DeleteAccountPage deleteAccount()
	{
		deleteAccount.click();
		return new DeleteAccountPage(driver);
	}
	
	public String getLoggedInAsText()
	{
		return loggedInAs.getText();
	}
	
	public ContactPage goToContactUs()
	{
		contactUs.click();
		return new ContactPage(driver);
	}
	
	//Footer Methods
	
	public String getSubscriptionText()
	{
		return subscriptionText.getText();
	}
	
	public void enterSubscribeEmail(String email)
	{
		emailEditBox.sendKeys(email);
	}
	
	public void submitSubscription()
	{
		subscribeBtn.click();
	}
	
	public WebElement getSubcribeButtonEle()
	{
		return subscribeBtn;
	}
	
	public String getSuccessAlert()
	{
		return subSuccessAlert.getText();
	}
	
	public void scrollToFooter()
	{
		js.executeScript("arguments[0].scrollIntoView()", footer);
	}
	
	//Add to cart popup
	public void continueShopping()
	{
		continueShopping.click();
	}
	
	public CartPage viewCartAfterAdding()
	{
		viewCartPopup.click();
		return new CartPage(driver);
	}
	
	
}
