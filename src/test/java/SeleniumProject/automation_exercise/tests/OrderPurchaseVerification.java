package SeleniumProject.automation_exercise.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountCreatedPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.DeleteAccountPage;
import pageobjects.HomePage;
import pageobjects.PaymentDonePage;
import pageobjects.PaymentPage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;
import pageobjects.SignUpPage;
import testcomponents.BaseTest;

public class OrderPurchaseVerification extends BaseTest {
	
	OrderPurchaseVerification opv;
	ProductsPage pp;

	@Test
	public void AddProductsToCart() throws InterruptedException 
	{
		int productCount = 0;
		ArrayList<Integer> productPagePrices = new ArrayList<Integer>();
		HashMap<Integer, Integer> quantityMap = new HashMap<Integer, Integer>();
		
		pp = homepage.goToProducts();
		pp.addToCartByIndex(0);
		productCount++;
		productPagePrices.add(pp.getProductPriceByIndex(0));
		quantityMap.put(0, 1);
		pp.continueShopping();
		
		pp.addToCartByIndex(1);
		productCount++;
		productPagePrices.add(pp.getProductPriceByIndex(1));
		quantityMap.put(1, 1);
		CartPage cart = pp.viewCartAfterAdding();
		
		Assert.assertEquals(cart.amountOfItemsInCart(), productCount);
		
		//Verify Prices in cart vs product page, Quantity, and total price per item
		for (int i = 0; i < cart.amountOfItemsInCart(); i++)
		{
			Assert.assertEquals(productPagePrices.get(i), cart.getItemPriceByIndex(i));
			Assert.assertEquals(quantityMap.get(i), cart.getItemQuantityByIndex(i));
			
			int actualTotal = cart.getItemQuantityByIndex(i) * cart.getItemPriceByIndex(i);
			Assert.assertEquals(actualTotal, cart.getItemTotalPerIndex(i));
		}
	}
	
	@Test
	public void CartItemQuantityValidation() throws InterruptedException
	{
		int index = 4;
		int quantity = 4;
		
		pp = homepage.goToProducts();
		ProductDetailsPage pd = pp.viewProductByIndex(index);
		
		Assert.assertEquals(pd.getPageURL(), "https://www.automationexercise.com/product_details/" + (index+1));
		
		pd.changeQuantity(quantity);
		pd.addToCart();
		CartPage cart = pd.viewCartAfterAdding();
		
		Assert.assertTrue(cart.amountOfItemsInCart() > 0);
		Assert.assertEquals(cart.getItemQuantityByIndex(0), quantity);
		Assert.assertEquals(cart.getItemTotalPerIndex(0), cart.getItemPriceByIndex(0)*quantity);
	}
	
	@Test (dataProvider = "signup_data")
	public void RegisterAtCheckout(HashMap<String, String> data) throws InterruptedException
	{
		// Add items to cart then go to cart
		ArrayList<String> itemsOrdered = new ArrayList<String>();
		
		homepage.scrollDownABit();
		for (int i = 0; i < 3; i++)
		{
			itemsOrdered.add(homepage.getFeaturedItemNameByIndex(i));
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		CartPage cart = homepage.goToCart();
		
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		
		cart.proceedToCheckout();
		SignUpPage sp = cart.RegisterLoginCart();
		
		//Register user
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		Assert.assertTrue(sp.getEnterAccountInfoHeader().isDisplayed());
		sp.selectTitle(data.get("title"));
		sp.enterName(data.get("username2"));
		sp.enterPassword(data.get("password"));
		sp.enterDateOfBirth(Integer.parseInt(data.get("day")), data.get("month"), data.get("year"));
		sp.joinNewsletter();
		sp.receiveSpecialOffers();
		sp.enterFirstName(data.get("firstname"));
		sp.enterLastName(data.get("lastname"));
		sp.enterCompany(data.get("company"));
		sp.enterAddress1(data.get("address"));
		sp.enterAddress2(data.get("address2"));
		sp.enterCountry(data.get("country"));
		sp.enterState(data.get("state"));
		sp.enterCity(data.get("city"));
		sp.enterZipcode(data.get("zipcode"));
		sp.enterMobileNumber(data.get("mobilenumber"));
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.getAccountCreatedEle().isDisplayed());
		homepage = acp.clickContinue();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		//Return to checkout
		homepage.goToCart();
		CheckoutPage ck = cart.proceedToCheckout();
		
		//Verify delivery address info is correct
		Assert.assertEquals(data.get("title"), ck.getDeliveryUserTitle());
		Assert.assertEquals(data.get("firstname"), ck.getDeliveryFirstName());
		Assert.assertEquals(data.get("lastname"), ck.getDeliveryLastName());
		Assert.assertEquals(data.get("company"), ck.getDeliveryCompany());
		Assert.assertEquals(data.get("address"), ck.getDeliveryAddress1());
		Assert.assertEquals(data.get("address2"), ck.getDeliveryAddress2());
		Assert.assertTrue(ck.getDeliveryCityStateZip().contains(data.get("city")));
		Assert.assertTrue(ck.getDeliveryCityStateZip().contains(data.get("state")));
		Assert.assertTrue(ck.getDeliveryCityStateZip().contains(data.get("zipcode")));
		
		//Verify billing address info is correct
		Assert.assertEquals(data.get("title"), ck.getBillingUserTitle());
		Assert.assertEquals(data.get("firstname"), ck.getBillingFirstName());
		Assert.assertEquals(data.get("lastname"), ck.getBillingLastName());
		Assert.assertEquals(data.get("company"), ck.getBillingCompany());
		Assert.assertEquals(data.get("address"), ck.getBillingAddress1());
		Assert.assertEquals(data.get("address2"), ck.getBillingAddress2());
		Assert.assertTrue(ck.getBillingCityStateZip().contains(data.get("city")));
		Assert.assertTrue(ck.getBillingCityStateZip().contains(data.get("state")));
		Assert.assertTrue(ck.getBillingCityStateZip().contains(data.get("zipcode")));
		
		//Verify Checkout items
		for(int i = 0; i < ck.amountOfItemsInCart(); i++)
		{
			Assert.assertEquals(itemsOrdered.get(i), ck.getItemNameByIndex(i));
		}
		
		//Verify price
		double price = 0;
		for (int i = 0; i < ck.amountOfItemsInCart(); i++)
		{
			price += ck.getItemTotalPerIndex(i);
		}
		
		Assert.assertEquals(price, ck.getOrderTotal());
		
		ck.addComment("this is a test order");
		
		PaymentPage pay = ck.placeOrder();
		pay.enterNameOnCard(data.get("nameoncard"));
		pay.enterCardNumber(data.get("cardnumber"));
		pay.enterCVC(data.get("cvc"));
		pay.enterExpMonth(data.get("expmonth"));
		pay.enterExpYear(data.get("expyear"));
		PaymentDonePage pdp = pay.payAndConfirmOrder();
		
		Assert.assertEquals(pdp.getOrderPlacedText(), "ORDER PLACED!");
		//Assert.assertEquals(pay.getSuccessMessage(), "Your order has been placed successfully!");
		
		DeleteAccountPage dap = pdp.deleteAccount();
		Assert.assertEquals(dap.getAccountDeletedText(), "ACCOUNT DELETED!");
		dap.clickContinue();
		
	}	
		
		
	@DataProvider (name="signup_data")
	public Object[][] signupTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\signup_data.json";
				
		
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
		
	}	

}
