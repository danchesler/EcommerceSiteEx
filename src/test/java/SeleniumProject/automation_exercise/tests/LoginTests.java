package SeleniumProject.automation_exercise.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.SignUpPage;
import testcomponents.BaseTest;

public class LoginTests extends BaseTest {
	
	
	@Test
	public void RegisterUser()
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getSignupHeaderElement().getText(),"New User Signup!");
		
		
	}

	
	@DataProvider
	public Object[][] testData()
	{
		return null;
		
	}

}
