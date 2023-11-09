package SeleniumProject.automation_exercise.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountCreatedPage;
import pageobjects.DeleteAccountPage;
import pageobjects.HomePage;
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
		
		sp.selectTitle(data.get("title"));
		sp.enterName(data.get("username2"));
		sp.enterPassword(data.get("password"));
		
		sp.enterDateOfBirth(Integer.parseInt(data.get("day")), data.get("month"), data.get("year"));
		sp.joinNewsletter();
		sp.receiveSpecialOffers();
		sp.enterFirstName(data.get("firstname"));
		sp.enterLastName(data.get("lastname"));
		sp.enterCompany(data.get("company"));
		sp.enterAddress1(data.get("address"));
		sp.enterAddress2(data.get("address2"));
		sp.enterCountry(data.get("country"));
		sp.enterState(data.get("state"));
		sp.enterCity(data.get("city"));
		sp.enterZipcode(data.get("zipcode"));
		sp.enterMobileNumber(data.get("mobilenumber"));
		
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.getAccountCreatedEle().isDisplayed());
		
		HomePage hp = acp.clickContinue();
		Assert.assertEquals(hp.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		DeleteAccountPage dap =  hp.deleteAccount();
		Assert.assertTrue(dap.getAccountDeletedEle().isDisplayed());
		dap.clickContinue();
		
		
	}

	@Test (dataProvider = "signup_data", dependsOnMethods = {"RegisterUserThenLogout"})
	public void LoginThenDeleteUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getLoginHeaderEle().getText(),"Login to your account");
		
		sp.enterExistingUserInfo(data.get("email"), data.get("password"));
		sp.login();
		
		DeleteAccountPage dap = sp.deleteAccount();
		Assert.assertTrue(dap.getAccountDeletedEle().isDisplayed());
		dap.clickContinue();
		
	}
	
	@Test (dataProvider = "signup_data")
	public void LoginInvalidUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		sp.enterExistingUserInfo(data.get("email"), data.get("password"));
		sp.login();
		
		Assert.assertEquals(sp.getIncorrectLoginText(), "Your email or password is incorrect!");
	}
	
	@Test (dataProvider = "login_data")
	public void Logout (HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		Assert.assertEquals(sp.getLoginHeaderEle().getText(),"Login to your account");
		
		sp.enterExistingUserInfo(data.get("email"), data.get("password"));
		HomePage hp = sp.login();
		
		Assert.assertEquals(hp.getLoggedInAsText(), "Logged in as " + data.get("username2"));
		
		sp = hp.Logout();
		
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
		
		sp.selectTitle(data.get("title"));
		sp.enterName(data.get("username2"));
		sp.enterPassword(data.get("password"));
		
		sp.enterDateOfBirth(Integer.parseInt(data.get("day")), data.get("month"), data.get("year"));
		sp.joinNewsletter();
		sp.receiveSpecialOffers();
		sp.enterFirstName(data.get("firstname"));
		sp.enterLastName(data.get("lastname"));
		sp.enterCompany(data.get("company"));
		sp.enterAddress1(data.get("address"));
		sp.enterAddress2(data.get("address2"));
		sp.enterCountry(data.get("country"));
		sp.enterState(data.get("state"));
		sp.enterCity(data.get("city"));
		sp.enterZipcode(data.get("zipcode"));
		sp.enterMobileNumber(data.get("mobilenumber"));
		
		AccountCreatedPage acp = sp.createAccount();
		Assert.assertTrue(acp.getAccountCreatedEle().isDisplayed());
		
		HomePage hp = acp.clickContinue();
		hp.Logout();
		
		driver.close();
		
	}
	
	@Test (dataProvider = "login_data", alwaysRun=false)
	public void DeleteExistingUser(HashMap<String,String> data)
	{
		SignUpPage sp = homepage.goToSignUp();
		sp.enterExistingUserInfo(data.get("email"), data.get("password"));
		sp.login();
		DeleteAccountPage dap = sp.deleteAccount();
		dap.clickContinue();
	}
	
	
	@DataProvider (name="signup_data")
	public Object[][] signupTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\signup_data.json";
				
		
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
		
	}
	
	@DataProvider (name="login_data")
	public Object[][] loginTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\login_data.json";
				
		
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
	}
	

}
