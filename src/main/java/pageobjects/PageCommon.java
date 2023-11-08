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

	//Header links
	public SignUpPage goToSignUp()
	{
		signupLink.click();
		return new SignUpPage(driver);
	}
}
