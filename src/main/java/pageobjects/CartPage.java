package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends PageCommon {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".breadcrumb .active")
	private WebElement trailHeader;
	
	@FindBy(css=".check_out")
	private WebElement checkout;
	
	@FindBy (css="tbody tr[id*='product']")
	private List<WebElement> cartList;
	
	@FindBy (css="h4 a")
	private List<WebElement> cartProductNames;
	
	@FindBy(css=".cart_price p")
	private List<WebElement> cartPrices;
	
	@FindBy(css=".cart_quantity button")
	private List<WebElement> cartQuantities;
	
	@FindBy(css="tbody tr[id*='product'] .cart_total_price")
	private List<WebElement> cartTotals;
	
	@FindBy(css=".fa-times")
	private List<WebElement> cartRemoveItem;
	
	//Checkout popup
	
	@FindBy(css=".modal-content u")
	private WebElement registerLogin;
	
	public String getTrailHeaderText()
	{
		return trailHeader.getText();
	}
	
	public CheckoutPage proceedToCheckout()
	{
		checkout.click();
		
		return new CheckoutPage(driver);
	}
	
	public SignUpPage registerLoginCart()
	{
		registerLogin.click();
		
		return new SignUpPage(driver);
	}
	
	public int amountOfItemsInCart()
	{
		return cartList.size();
	}
	
	public String getItemNameByIndex(int index)
	{
		String itemName = cartProductNames.get(index).getText();
	
		return itemName;
	}
	
	public int getItemPriceByIndex(int index)
	{
		return removeDollarFromPriceStr(cartPrices.get(index));
	}
	
	public int getItemQuantityByIndex(int index)
	{
		WebElement quantityEle = cartQuantities.get(index);
		String quantityStr = quantityEle.getText();
		int quantity = Integer.parseInt(quantityStr); 
		
		return quantity;
	}
	
	public int getItemTotalPerIndex(int index)
	{
		return removeDollarFromPriceStr(cartTotals.get(index));
	}
	
	public void removeFirstCartitem()
	{
		cartRemoveItem.get(0).click();
	}
	
	
}
