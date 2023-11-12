package SeleniumProject.automation_exercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;
import testcomponents.BaseTest;

public class ProductsTests extends BaseTest {

	ProductsPage pp;
	
	@Test
	public void ViewProductsPage()
	{
		pp = homepage.goToProducts();
		Assert.assertEquals(pp.getPageTitle(), "Automation Exercise - All Products");
		Assert.assertEquals(pp.getProductsPageTitle(), "ALL PRODUCTS");
	}
	
	@Test (dependsOnMethods = {"ViewProductsPage"})
	public void ViewFirstProduct()
	{
		ProductDetailsPage pd = pp.viewProductByIndex(0);
		
		Assert.assertTrue(pd.isCategoryDisplayed());
		Assert.assertTrue(pd.isPriceDisplayed());
		Assert.assertTrue(pd.isAvailabilityDisplayed());
		Assert.assertTrue(pd.isConditionDisplayed());
		Assert.assertTrue(pd.isBrandDisplayed());
	}
	
	@Test (dataProvider="product_data", dependsOnMethods = {"ViewProductsPage"})
	public void SearchForProducts(HashMap<String,String> data)
	{
		pp.searchProduct(data.get("product"));
		
		boolean isValidSearch = true;
		
		for (WebElement e : pp.getProductNames())
		{
			if (!e.getText().toLowerCase().contains(data.get("product")))
			{
				int index = pp.getProductNames().indexOf(e);
				ProductDetailsPage pd = pp.viewProductByIndex(index);
				
				if (!pd.getCategory().toLowerCase().contains(data.get("product")))
				{
					isValidSearch = false;
					break;
				}
				pd.goPrevPage();
			}
		}
		
		Assert.assertTrue(isValidSearch);
	}
	
	@Test
	public void RemoveProductsFromCart() throws InterruptedException
	{
		homepage.scrollDownABit();
		for (int i = 0; i < 3; i++)
		{
			homepage.addToCartByIndex(i);
			homepage.continueShopping();
		}
		
		CartPage cart = homepage.goToCart();
		Assert.assertEquals(cart.getTrailHeaderText(), "Shopping Cart");
		
		int initialCartSize = cart.amountOfItemsInCart();
		
		cart.removeFirstCartitem();
		Thread.sleep(500);
		
		Assert.assertTrue(cart.amountOfItemsInCart() < initialCartSize);
	}
	
	@Test (dataProvider = "category_data")
	public void CategorizeProductsVerification(HashMap<String, String> data)
	{
		Assert.assertTrue(homepage.areCategoriesDisplayed());
		
		homepage.selectCategory(data.get("category"));
		
		switch (data.get("category"))
		{
			case "WOMEN": 
				pp = homepage.selectWomenSubCategory(data.get("subcategory"));
				break;
			case "MEN": 
				pp = homepage.selectMenSubCategory(data.get("subcategory"));
				break;
			case "KIDS": 
				pp = homepage.selectKidsSubCategory(data.get("subcategory"));
				break;
				
		}
		Assert.assertTrue(pp.getPageURL().contains("category_products"));
		
		String expectedTitle = data.get("category") + " - " + data.get("subcategory") + " PRODUCTS";
		Assert.assertEquals(pp.getProductsPageTitle(), expectedTitle);
		
	}
	
	
	
	@DataProvider (name="product_data")
	public Object[][] signupTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\searchEntry.json";
				
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
	}
	
	@DataProvider (name="category_data")
	public Object[][] categoryTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\category_data.json";
				
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)}, {data.get(1)}, {data.get(2)} };
	}
	
}
