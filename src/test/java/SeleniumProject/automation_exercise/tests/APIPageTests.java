package SeleniumProject.automation_exercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.APIPage;
import testcomponents.BaseTest;

public class APIPageTests extends BaseTest {
	APIPage api;
	
	@Test
	public void NavigateToAPIPage() {
		api = homepage.goToAPITesting();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/api_list");
		Assert.assertEquals(api.getAPIPageTitle(), "APIS LIST FOR PRACTICE");
	}
	
	@Test (dependsOnMethods="NavigateToAPIPage")
	public void BrowseTestCases() throws InterruptedException {
		for (int i = 0; i < api.getAmountofAPIcases(); i++) {
			api.openThenCloseAPICase(i);
		}
		
	}
	
}
