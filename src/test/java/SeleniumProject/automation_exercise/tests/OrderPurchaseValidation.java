package SeleniumProject.automation_exercise.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountCreatedPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.DeleteAccountPage;
import pageobjects.PaymentDonePage;
import pageobjects.PaymentPage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;
import pageobjects.SignUpPage;
import testcomponents.BaseTest;

public class OrderPurchaseValidation extends BaseTest {
	
	OrderPurchaseValidation opv;
	ProductsPage pp;

	@BeforeMethod
	public void goToHomePage() throws InterruptedException {
		homepage.goToHomepage();
	}
	
	@Test
	public void AddProductsToCart() throws InterruptedException {
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
		for (int i = 0; i < cart.amountOfItemsInCart(); i++) {
			Assert.assertEquals(productPagePrices.get(i), cart.getItemPriceByIndex(i));
			Assert.assertEquals(quantityMap.get(i), cart.getItemQuantityByIndex(i));
			
			int actualTotal = cart.getItemQuantityByIndex(i) * cart.getItemPriceByIndex(i);
			Assert.assertEquals(actualTotal, cart.getItemTotalPerIndex(i));
		}
	}
	
	@Test
	public void CartItemQuantityValidation() throws InterruptedException {
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
	
	@Test (dataProvider = "signup_data", groups = "e2e_purchase")
	public void PlaceOrderRegisterAtCheckout(HashMap<String, String> data) throws InterruptedException {
		homepage.goToHomePageFromLogo();
		
		// Add items to cart then go to cart
		ArrayList<String> itemsOrdered = new ArrayList<String>();
		homepage.scrollDownABit();
		for (int i = 0; i < 3; i++) {
			itemsOrdered.add(homepage.getFeaturedItemNameByIndex(i));
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		CartPage cart = homepage.goToCart();
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		cart.proceedToCheckout();
		SignUpPage sp = cart.registerLoginCart();
		
		//Register user
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		Assert.assertTrue(sp.getEnterAccountInfoHeader().isDisplayed());
		sp.enterSignUpDetails(sp, data);
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.isAccountCreatedDisplayed());
		homepage = acp.clickContinue();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		//Return to checkout
		homepage.goToCart();
		CheckoutPage ck = cart.proceedToCheckout();
		
		VerifyAddressAtCheckout(data,ck);
		
		//Verify Checkout items
		for(int i = 0; i < ck.amountOfItemsInCart(); i++) {
			Assert.assertEquals(itemsOrdered.get(i), ck.getItemNameByIndex(i));
		}
		
		//Verify price
		double price = 0;
		for (int i = 0; i < ck.amountOfItemsInCart(); i++) {
			price += ck.getItemTotalPerIndex(i);
		}
		
		Assert.assertEquals(price, ck.getOrderTotal());
		
		ck.addComment("this is a test order");
		
		PaymentPage pay = ck.placeOrder();
		pay.enterPaymentDetails(pay, data);
		PaymentDonePage pdp = pay.payAndConfirmOrder();
		
		Assert.assertEquals(pdp.getOrderPlacedText(), "ORDER PLACED!");
		//Assert.assertEquals(pay.getSuccessMessage(), "Your order has been placed successfully!");
		
		DeleteAccountPage dap = pdp.deleteAccount();
		Assert.assertEquals(dap.getAccountDeletedText(), "ACCOUNT DELETED!");
		dap.clickContinue();
	}	
		
	@Test (dataProvider = "signup_data", groups = "e2e_purchase")
	public void PlaceOrderRegisterBeforeCheckout(HashMap<String, String> data) throws InterruptedException {
		//Register user
		SignUpPage sp = homepage.goToSignUp();
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		Assert.assertTrue(sp.getEnterAccountInfoHeader().isDisplayed());
		sp.enterSignUpDetails(sp, data);
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.isAccountCreatedDisplayed());
		homepage = acp.clickContinue();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		ArrayList<String> itemsOrdered = new ArrayList<String>();
		
		homepage.scrollDownABit();
		for (int i = 0; i < 3; i++) {
			itemsOrdered.add(homepage.getFeaturedItemNameByIndex(i));
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		CartPage cart = homepage.goToCart();
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		CheckoutPage ck = cart.proceedToCheckout();
		
		VerifyAddressAtCheckout(data,ck);
		
		//Verify Checkout items
		for(int i = 0; i < ck.amountOfItemsInCart(); i++) {
			Assert.assertEquals(itemsOrdered.get(i), ck.getItemNameByIndex(i));
		}
		
		//Verify price
		double price = 0;
		for (int i = 0; i < ck.amountOfItemsInCart(); i++) {
			price += ck.getItemTotalPerIndex(i);
		}
		Assert.assertEquals(price, ck.getOrderTotal());
		
		ck.addComment("this is a test order");
		PaymentPage pay = ck.placeOrder();
		pay.enterPaymentDetails(pay, data);
		PaymentDonePage pdp = pay.payAndConfirmOrder();
		
		Assert.assertEquals(pdp.getOrderPlacedText(), "ORDER PLACED!");
		
		DeleteAccountPage dap = pdp.deleteAccount();
		Assert.assertEquals(dap.getAccountDeletedText(), "ACCOUNT DELETED!");
		dap.clickContinue();
	}
		
	
	@Test (dataProvider = "login_data", groups = "e2e_purchase")
	public void LoginBeforeCheckout(HashMap<String, String> data) throws InterruptedException {
		SignUpPage sp = homepage.goToSignUp();
		sp.enterLoginDetails(data.get("email"), data.get("password"));
		homepage = sp.submitLogin();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		homepage.scrollDownABit();
		for (int i = 0; i < 3; i++) {
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		
		CartPage cart = homepage.goToCart();
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		CheckoutPage ck = cart.proceedToCheckout();
		
		VerifyAddressAtCheckout(data, ck);
		
		ck.addComment("this is a product review");
		PaymentPage pay = ck.placeOrder();
		
		pay.enterPaymentDetails(pay, data);
		PaymentDonePage pdp = pay.payAndConfirmOrder();
		
		Assert.assertEquals(pdp.getOrderPlacedText(), "ORDER PLACED!");
		
		homepage = pdp.continueShopping();
		
		sp = homepage.Logout();
		sp.goToHomePageFromLogo();
	}
	
	@Test (dataProvider = "signup_data")
	public void CheckoutAddressVerification(HashMap<String, String> data) throws InterruptedException {
		SignUpPage sp = homepage.goToSignUp();
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		sp.enterSignUpDetails(sp, data);
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.isAccountCreatedDisplayed());
		homepage = acp.clickContinue();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		for (int i = 0 ; i < 3; i++) {
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		
		CartPage cart = homepage.goToCart();
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		CheckoutPage ck = cart.proceedToCheckout();	
		
		VerifyAddressAtCheckout(data, ck);
		
		DeleteAccountPage dap = ck.deleteAccount();
		Assert.assertEquals(dap.getAccountDeletedText(), "ACCOUNT DELETED!");
		dap.clickContinue();
	}
	
	@Test (dataProvider = "signup_data")
	public void DownloadInvoiceAfterPurchase(HashMap<String, String> data) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		
		CartPage cart = homepage.goToCart();
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		cart.proceedToCheckout();	
		
		SignUpPage sp = cart.registerLoginCart();
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		sp.enterSignUpDetails(sp, data);
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.isAccountCreatedDisplayed());
		homepage = acp.clickContinue();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		cart = homepage.goToCart();
		CheckoutPage ck = cart.proceedToCheckout();
		
		VerifyAddressAtCheckout(data, ck);
		
		ck.addComment("i'm going to buy this");
		PaymentPage pay = ck.placeOrder();

		pay.enterPaymentDetails(pay, data);
		PaymentDonePage pdp = pay.payAndConfirmOrder();
		
		Assert.assertEquals(pdp.getOrderPlacedText(), "ORDER PLACED!");
	
		pdp.downloadInvoice();
		Thread.sleep(2000); 
		
		//verify file is downloaded
		File f = new File(System.getProperty("user.dir") + "\\invoice.txt");
		if (f.exists()) {
			Assert.assertTrue(true);
			f.delete();
		}
		else {
			Assert.assertTrue(f.exists());
		}

		homepage = pdp.continueShopping();
		
		DeleteAccountPage dap = homepage.deleteAccount();
		Assert.assertEquals(dap.getAccountDeletedText(), "ACCOUNT DELETED!");
		dap.clickContinue();
	}
	
	@DataProvider (name="signup_data")
	public Object[][] signupTestData() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\user_data.json";
				
		
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
	}	
	
	@DataProvider (name="login_data")
	public Object[][] loginTestData() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\user_data.json";
				
		
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(1)} };
	}	

	public void VerifyAddressAtCheckout(HashMap<String, String> data, CheckoutPage ck) {
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
	}
	
}
