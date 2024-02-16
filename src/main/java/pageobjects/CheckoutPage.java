package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends CartPage {

	private WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td/h4/b/parent::h4/parent::td/following-sibling::td/p")
	private WebElement orderTotal;
	
	@FindBy(name="message")
	private WebElement commentBox;
	
	@FindBy(css=".check_out")
	private WebElement placeOrder;
	
	//Delivery address locators
	
	//title firstname lastname
	@FindBy (css="#address_delivery [class*='stname']")
	private WebElement deliveryTitleAndName;
	
	//[0] company, [1] address1, [2] address2
	@FindBy(css="#address_delivery [class*='address2']")
	private List<WebElement> deliveryAddress;
	
	//contains city state zipcode
	@FindBy(css="#address_delivery .address_city")
	private WebElement deliveryCityStateZip;
	
	@FindBy(css="#address_delivery [class*='country']")
	private WebElement deliveryCountry;
	
	@FindBy(css="#address_delivery [class*='phone']")
	private WebElement deliveryPhone;
	
	//Billing address locators
	
	//title firstname lastname
	@FindBy (css="#address_invoice [class*='stname']")
	private WebElement billTitleAndName;
	
	//[0] company, [1] address1, [2] address2
	@FindBy(css="#address_invoice [class*='address2']")
	private List<WebElement> billAddress;
	
	//contains city state zipcode
	@FindBy(css="#address_invoice .address_city")
	private WebElement billCityStateZip;
	
	@FindBy(css="#address_invoice [class*='country']")
	private WebElement billCountry;
	
	@FindBy(css="#address_invoice [class*='phone']")
	private WebElement billPhone;
	
	public int getOrderTotal() {
		return removeDollarFromPriceStr(orderTotal);
	}
	
	public void addComment(String comment) {
		commentBox.sendKeys(comment);
	}
	
	public PaymentPage placeOrder() {
		placeOrder.click();
		
		return new PaymentPage(driver);
	}
	
	//Delivery getters
	
	public String getDeliveryUserTitle() {
		String titleAndName = deliveryTitleAndName.getText();
		String title = titleAndName.split(" ")[0];
		
		return title;
	}
	
	public String getDeliveryFirstName() {
		String titleAndName = deliveryTitleAndName.getText();
		String firstName = titleAndName.split(" ")[1];
		
		return firstName;
	}
	
	public String getDeliveryLastName() {
		String titleAndName = deliveryTitleAndName.getText();
		String lastName = titleAndName.split(" ")[2];
		
		return lastName;
	}
	
	public String getDeliveryCompany() {
		String company = deliveryAddress.get(0).getText();
		
		return company;
	}
	
	public String getDeliveryAddress1() {
		String address1 = deliveryAddress.get(1).getText();
		
		return address1;
	}
	
	public String getDeliveryAddress2() {
		String address2 = deliveryAddress.get(2).getText();
		
		return address2;
	}
	
	public String getDeliveryCityStateZip() {
		String cityStateZip = deliveryCityStateZip.getText();
		
		return cityStateZip;
	}
	
	public String getDeliveryPhoneNumber() {
		String phoneNumber = deliveryPhone.getText();
		
		return phoneNumber;
	}
	
	//Billing getters
	
	public String getBillingUserTitle() {
		String titleAndName = billTitleAndName.getText();
		String title = titleAndName.split(" ")[0];
		
		return title;
	}
	
	public String getBillingFirstName() {
		String titleAndName = billTitleAndName.getText();
		String firstName = titleAndName.split(" ")[1];
		
		return firstName;
	}
	
	public String getBillingLastName() {
		String titleAndName = billTitleAndName.getText();
		String lastName = titleAndName.split(" ")[2];
		
		return lastName;
	}
	
	public String getBillingCompany() {
		String company = billAddress.get(0).getText();
		
		return company;
	}
	
	public String getBillingAddress1() {
		String address1 = billAddress.get(1).getText();
		
		return address1;
	}
	
	public String getBillingAddress2() {
		String address2 = billAddress.get(2).getText();
		
		return address2;
	}
	
	public String getBillingCityStateZip() {
		String cityStateZip = billCityStateZip.getText();
		
		return cityStateZip;
	}
	
	public String getBillingPhoneNumber() {
		String phoneNumber = billPhone.getText();
		
		return phoneNumber;
	}
}
