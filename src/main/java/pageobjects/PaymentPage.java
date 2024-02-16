package pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends PageCommon {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="name_on_card")
	private WebElement nameField;
	
	@FindBy(name="card_number")
	private WebElement cardNumberField;
	
	@FindBy(name="cvc")
	private WebElement cvcField;
	
	@FindBy(name="expiry_month")
	private WebElement expMonthField;
	
	@FindBy(name="expiry_year")
	private WebElement expYearField;

	@FindBy(id="submit")
	private WebElement confirmOrder;
	
	@FindBy(css="#success_message")
	private WebElement orderSuccess;
	
	public void enterNameOnCard(String name) {
		nameField.sendKeys(name);
	}
	
	public void enterCardNumber(String number) {
		cardNumberField.sendKeys(number);
	}
	
	public void enterCVC(String cvc) {
		cvcField.sendKeys(cvc);
	}
	
	public void enterExpMonth(String month) {
		expMonthField.sendKeys(month);
	}
	
	public void enterExpYear(String year) {
		expYearField.sendKeys(year);
	}
	
	public PaymentDonePage payAndConfirmOrder() {
		confirmOrder.click();
		
		return new PaymentDonePage(driver);
	}
	
	public void enterPaymentDetails(PaymentPage pay, HashMap<String,String> data) {
		pay.enterNameOnCard(data.get("nameoncard"));
		pay.enterCardNumber(data.get("cardnumber"));
		pay.enterCVC(data.get("cvc"));
		pay.enterExpMonth(data.get("expmonth"));
		pay.enterExpYear(data.get("expyear"));
	}
	
	public WebElement getSuccessElement() {
		return orderSuccess;
	}
	
	public String getSuccessMessage() {
		return orderSuccess.getText();
	}
}
