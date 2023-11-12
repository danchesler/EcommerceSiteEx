package SeleniumProject.automation_exercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.ContactPage;
import testcomponents.BaseTest;

public class ContactUsTests extends BaseTest {

	@Test (dataProvider = "contactus_data")
	public void FillContactUsForm(HashMap<String,String> data) throws IOException, InterruptedException
	{
		ContactPage con = homepage.goToContactUs();
		
		Assert.assertTrue(con.getInTouchIsDisplayed());
		
		con.enterName(data.get("name"));
		con.enterEmail(data.get("email"));
		con.enterSubject(data.get("subject"));
		con.enterMessage(data.get("message"));
		con.uploadFile();
		con.submitForm();
		con.acceptAlert();
		
		Assert.assertEquals(con.getSuccessMsg(), "Success! Your details have been submitted successfully.");
	
		con.goToHomePage();
	}	
	
	@DataProvider (name="contactus_data")
	public Object[][] signupTestData() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\contactus_data.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)} };
		
	}
	
}
