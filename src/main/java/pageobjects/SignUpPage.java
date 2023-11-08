package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends PageCommon {

	WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".signup-form h2")
	private WebElement signupHeader;

	public WebElement getSignupHeaderElement()
	{
		return signupHeader;
	}
	
}
