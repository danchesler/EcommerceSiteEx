package SeleniumProject.automation_exercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.APIPage;
import testcomponents.BaseTest;

public class APIPageTests extends BaseTest {
	APIPage api;
	
	@Test (invocationCount = 3)
	public void NavigateToAPIPage() throws InterruptedException {
		api = homepage.goToAPITesting();
	
		Assert.assertEquals(api.getAPIPageTitle(), "APIS LIST FOR PRACTICE");
		
		api.goToHomePageFromLogo();
	}
	
	@Test (dependsOnMethods="NavigateToAPIPage")
	public void BrowseTestCases() throws InterruptedException {
		for (int i = 0; i < api.getAmountofAPIcases(); i++) {
			api.openThenCloseAPICase(i);
		}
		
	}
	
}
