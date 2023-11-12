package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends PageCommon {
	
	WebDriver driver;
	
	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="b")
	private WebElement accountCreatedText;
	
	@FindBy(css="[data-qa*='continue']")
	private WebElement continueBtn;
	
	public boolean isAccountCreatedDisplayed()
	{
		return accountCreatedText.isDisplayed();
	}
	
	public HomePage clickContinue()
	{
		continueBtn.click();
		return new HomePage(driver);
	}
	
}
