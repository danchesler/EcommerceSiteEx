package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageCommon {

	WebDriver driver;
	Actions a;
	
	public PageCommon (WebDriver driver)
	{
		a = new Actions(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a[href*='/products']")
	private WebElement products;
	
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
	
	public String getPageURL()
	{
		return driver.getCurrentUrl();
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	//Header links
	
	public ProductsPage goToProducts()
	{
		a.moveToElement(products).doubleClick().build().perform();
		return new ProductsPage(driver);
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
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
}
