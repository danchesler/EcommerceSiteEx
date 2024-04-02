package SeleniumProject.automation_exercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.APIPage;
import testcomponents.BaseTest;

public class APIPageTests extends BaseTest {
	APIPage api;
	
	@Test
	public void NavigateToAPIPage() throws InterruptedException {
		api = homepage.goToAPITesting();
	
		Assert.assertEquals(api.getAPIPageTitle(), "APIS LIST FOR PRACTICE");
	}
	
	@Test (dependsOnMethods="NavigateToAPIPage")
	public void BrowseAPITestCases() throws InterruptedException {
//		for (int i = 0; i < api.getAmountofAPIcases(); i++) {
//			api.openThenCloseAPICase(i);
//		}
		for (int i = 0; i < 3; i++) {
			api.openThenCloseAPICase(i);
		}
	}
	
}
