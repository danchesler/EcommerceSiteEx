package SeleniumProject.automation_exercise.tests;

import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.YouTubePage;
import testcomponents.BaseTest;

public class VideoTutorialsTests extends BaseTest {

	@Test
	public void VerifyYouTubePage () {
		YouTubePage ytp = homepage.goToYouTubePage();
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> itr = handles.iterator();
		String homeTab = itr.next();
		String youtubeTab = itr.next();
		
		driver.switchTo().window(youtubeTab);
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(ytp.isChannelNameDisplayed());
		
		driver.switchTo().window(homeTab);
		
		soft.assertAll();
	}
	
}
