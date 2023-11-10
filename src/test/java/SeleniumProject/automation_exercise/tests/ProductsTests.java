package SeleniumProject.automation_exercise.tests;

import org.testng.Assert;
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
	
}
