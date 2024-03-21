package SeleniumProject.automation_exercise.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.YouTubePage;
import testcomponents.BaseTest;

public class VideoTutorialsTests extends BaseTest {

	@Test
	public void VerifyYouTubePage () {
		YouTubePage ytp = homepage.goToYouTubePage();
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(ytp.isChannelNameDisplayed());
		
		ytp.goPrevPage();
		
		soft.assertAll();
	}
	
}
