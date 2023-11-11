package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends PageCommon {

	private WebDriver driver;
	
	public HomePage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".overlay-content .add-to-cart")
	private List<WebElement> overlayAddToCart;
	
	@FindBy(css=".productinfo")
	private List<WebElement> productInfoBox;
	
	@FindBy(css=".features_items .productinfo p")
	private List<WebElement> featuredItemNames;
	
	public void addToCartByIndex(int index) throws InterruptedException
	{
		a = new Actions(driver);
		a.moveToElement(productInfoBox.get(index)).build().perform();
		Thread.sleep(250);
		
		overlayAddToCart.get(index).click();
	}
	
	public String getFeaturedItemNameByIndex(int index)
	{
		return featuredItemNames.get(index).getText();
	}
	
	public void goToHomepage() throws InterruptedException
	{
		driver.get("https://www.automationexercise.com/");
		
		//wait for ads to go away
		Thread.sleep(3000);
		
		/*
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().contains("google"))
		{
			driver.navigate().back();
		}*/
	}
	
	
}
