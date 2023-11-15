package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentDonePage extends PageCommon {

	WebDriver driver;
	
	public PaymentDonePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="a[href*='invoice']")
	private WebElement downloadInvoice;
	
	@FindBy(css="a[data-qa*='continue']")
	private WebElement continueBtn;
	
	@FindBy(css="h2 b")
	private WebElement orderPlaced;
	
	
	public String getOrderPlacedText()
	{
		return orderPlaced.getText();
	}
	
	public HomePage continueShopping()
	{
		continueBtn.click();
		return new HomePage(driver);
	}
	
	public void downloadInvoice()
	{
		downloadInvoice.click();
	}
	
}
