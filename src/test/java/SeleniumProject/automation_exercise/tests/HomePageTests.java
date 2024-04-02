package SeleniumProject.automation_exercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import testcomponents.BaseTest;

public class HomePageTests extends BaseTest {

	@Test (priority=1)
	public void ScrollUpArrowFromFooter() {
		homepage.scrollToFooter();
		Assert.assertEquals(homepage.getSubscriptionText(), "SUBSCRIPTION");
		
		homepage.selectScrollUpArrow();
		Assert.assertTrue(homepage.isSliderDisplayed());
	}
	
	//default priority is 0
	@Test
	public void ScrollUpFromFooter() {
		homepage.scrollToFooter();
		Assert.assertEquals(homepage.getSubscriptionText(), "SUBSCRIPTION");
		
		homepage.scrollToTop();
		Assert.assertTrue(homepage.isSliderDisplayed());
	}
}
