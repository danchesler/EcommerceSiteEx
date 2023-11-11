package SeleniumProject.automation_exercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		Assert.assertEquals(pp.getProductsHeaderText(), "ALL PRODUCTS");
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
	public void test()
	{
		String g = "Test";
		System.out.println(g.contains("test"));
	}
	
	@DataProvider (name="product_data")
	public Object[][] signupTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\searchEntry.json";
				
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
		
	}
	
}
