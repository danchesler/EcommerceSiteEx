package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCommon {

	WebDriver driver;
	
	public PageCommon (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a[href*='login']")
	WebElement signupLink;

	@FindBy(xpath="//i[@class='fa fa-user']/parent::a")
	private WebElement loggedInAs;
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(linkText="Delete Account")
	private WebElement deleteAccount;
	
	
	public String getPageURL()
	{
		return driver.getCurrentUrl();
	}
	
	//Header links
	
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
	
	public DeleteAccountPage deleteAccount()
	{
		deleteAccount.click();
		return new DeleteAccountPage(driver);
	}
	
	public String getLoggedInAsText()
	{
		return loggedInAs.getText();
	}
	
}
