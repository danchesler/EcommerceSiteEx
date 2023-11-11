package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage extends PageCommon {

	WebDriver driver;
	
	public DeleteAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="b")
	public WebElement accountDeletedText;
	
	@FindBy(css="[data-qa*='continue']")
	public WebElement continueBtn;
	
	public WebElement getAccountDeletedEle()
	{
		return accountDeletedText;
	}
	
	public String getAccountDeletedText()
	{
		return accountDeletedText.getText();
	}
	
	public void clickContinue()
	{
		continueBtn.click();
	}
}
