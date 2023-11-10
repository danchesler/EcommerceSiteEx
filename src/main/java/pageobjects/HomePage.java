package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	public void goToHomepage() throws InterruptedException
	{
		driver.get("https://www.automationexercise.com/");
		
		//wait for ads to go away
		/*
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().contains("google"))
		{
			driver.navigate().back();
		}*/
	}
	
	
}
