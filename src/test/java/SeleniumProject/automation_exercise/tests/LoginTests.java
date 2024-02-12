package SeleniumProject.automation_exercise.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountCreatedPage;
import pageobjects.DeleteAccountPage;
import pageobjects.SignUpPage;
import testcomponents.BaseTest;

public class LoginTests extends BaseTest {
	
	
	@Test (dataProvider = "signup_data")
	public void RegisterThenDeleteUser(HashMap<String,String> data) throws InterruptedException
	{	
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getSignupHeaderEle().getText(),"New User Signup!");
		
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		
		Assert.assertTrue(sp.getEnterAccountInfoHeader().isDisplayed());
		sp.enterSignUpDetails(sp, data);
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.isAccountCreatedDisplayed());
		
		homepage = acp.clickContinue();
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		DeleteAccountPage dap =  homepage.deleteAccount();
		Assert.assertTrue(dap.getAccountDeletedEle().isDisplayed());
		dap.clickContinue();
		
		
	}

	@Test (dataProvider = "signup_data", dependsOnMethods = {"RegisterUserThenLogout"})
	public void LoginThenDeleteUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getLoginHeaderEle().getText(),"Login to your account");
		
		sp.enterLoginDetails(data.get("email"), data.get("password"));
		sp.submitLogin();
		
		DeleteAccountPage dap = sp.deleteAccount();
		Assert.assertTrue(dap.getAccountDeletedEle().isDisplayed());
		dap.clickContinue();
		
	}
	
	@Test (dataProvider = "signup_data")
	public void LoginInvalidUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		sp.enterLoginDetails(data.get("email"), data.get("password"));
		sp.submitLogin();
		
		Assert.assertEquals(sp.getIncorrectLoginText(), "Your email or password is incorrect!");
	}
	
	@Test (dataProvider = "login_data")
	public void Logout (HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getLoginHeaderEle().getText(),"Login to your account");
		
		sp.enterLoginDetails(data.get("email"), data.get("password"));
		homepage = sp.submitLogin();
		
		Assert.assertEquals(homepage.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		sp = homepage.Logout();
		
		Assert.assertEquals(sp.getPageURL(), "https://www.automationexercise.com/login");
		
	}
	
	@Test (dataProvider = "login_data")
	public void RegisterExistingUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getSignupHeaderEle().getText(),"New User Signup!");
		
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		
		Assert.assertEquals(sp.getEmailExistsText(), "Email Address already exist!");
	}
	
	//Used to setup a registered user
	@Test (dataProvider="signup_data")
	public void RegisterUserThenLogout(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getSignupHeaderEle().getText(),"New User Signup!");
		
		sp.enterNewUserInfo(data.get("username1"), data.get("email"));
		sp.submitNewUser();
		
		Assert.assertTrue(sp.getEnterAccountInfoHeader().isDisplayed());
		sp.enterSignUpDetails(sp, data);
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.isAccountCreatedDisplayed());
		
		homepage = acp.clickContinue();
		homepage.Logout();
		
		//driver.close();
		
	}
	
	@Test (dataProvider = "login_data", alwaysRun=false)
	public void DeleteExistingUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		sp.enterLoginDetails(data.get("email"), data.get("password"));
		homepage = sp.submitLogin();
		DeleteAccountPage dap = homepage.deleteAccount();
		dap.clickContinue();
	}
	
	
	
	//DataProviders
	@DataProvider (name="signup_data")
	public Object[][] signupTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\user_data.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
		
	}
	
	@DataProvider (name="login_data")
	public Object[][] loginTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\user_data.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(1)} };
	}
	
	

}
