package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends PageCommon {

	WebDriver driver;
	//String siteUrl = "https://www.automationexercise.com/";
	
	public HomePage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToHomepage()
	{
		driver.get("https://www.automationexercise.com/");
	}
	
}
