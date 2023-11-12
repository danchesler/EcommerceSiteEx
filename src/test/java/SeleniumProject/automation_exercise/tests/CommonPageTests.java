package SeleniumProject.automation_exercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import testcomponents.BaseTest;

public class CommonPageTests extends BaseTest {

	@Test (dataProvider = "user_data")
	public void SubscribeOnHomePage(HashMap<String, String> data)
	{
		homepage.scrollToFooter();
		Assert.assertEquals(homepage.getSubscriptionText(), "SUBSCRIPTION");
		
		homepage.enterSubscribeEmail(data.get("email"));
		homepage.submitSubscription();
		Assert.assertEquals(homepage.getSuccessAlert(), "You have been successfully subscribed!");
	}
	
	@Test (dataProvider = "user_data")
	public void SubscribeOnCartPage(HashMap<String, String> data)
	{
		CartPage c = homepage.goToCart();
		c.scrollToFooter();
		c.enterSubscribeEmail(data.get("email"));
		c.submitSubscription();
		Assert.assertEquals(homepage.getSuccessAlert(), "You have been successfully subscribed!");

	}
	
	@DataProvider (name="user_data")
	public Object[][] testData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\user_data.json";
				
		
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
		
	}

}
