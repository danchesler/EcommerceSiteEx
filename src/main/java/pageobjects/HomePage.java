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
	
	// Category sidebar locators
	
	@FindBy(className="category-products")
	private WebElement categoryBox;
	
	@FindBy(css="a[href='#Women']")
	private WebElement womenCategory;
	
	@FindBy(linkText="DRESS")
	private WebElement womenDress;
	
	@FindBy(linkText="TOPS")
	private WebElement womenTops;
	
	@FindBy(linkText="SAREE")
	private WebElement womenSaree;
	
	@FindBy(css="a[href='#Men']")
	private WebElement menCategory;
	
	@FindBy(linkText="TSHIRTS")
	private WebElement menTshirts;
	
	@FindBy(linkText="JEANS")
	private WebElement menJeans;
	
	@FindBy(css="a[href='#Kids']")
	private WebElement kidsCategory;
	
	@FindBy(linkText="DRESS")
	private WebElement kidsDress;
	
	@FindBy(linkText="TOPS & SHIRTS")
	private WebElement kidsTopsShirts;
	
	// Product Catalogue locators
	
	@FindBy(css=".overlay-content .add-to-cart")
	private List<WebElement> overlayAddToCart;
	
	@FindBy(css=".productinfo")
	private List<WebElement> productInfoBox;
	
	@FindBy(css=".features_items .productinfo p")
	private List<WebElement> featuredItemNames;
	
	public boolean areCategoriesDisplayed()
	{
		return categoryBox.isDisplayed();
	}
	
	public void selectCategory(String category)
	{
		switch (category) 
		{
		 case "WOMEN":
			 waitForElementToBeClickable(womenCategory);
			 womenCategory.click();
			 break;
		 case "MEN":
			 waitForElementToBeClickable(menCategory);
			 menCategory.click();
			 break;
		 case "KIDS":
			 waitForElementToBeClickable(kidsCategory);
			 kidsCategory.click();
			 break;
		}
	}
	
	public ProductsPage selectWomenSubCategory(String subCategory)
	{
		a = new Actions(driver);
		switch (subCategory)
		{
			case "DRESS":
				waitForElementToBeClickable(womenDress);
				a.moveToElement(womenDress).doubleClick().build().perform();
				break;
			case "TOPS":
				waitForElementToBeClickable(womenTops);
				a.moveToElement(womenTops).doubleClick().build().perform();
				break;
			case "SAREE":
				waitForElementToBeClickable(womenSaree);
				a.moveToElement(womenSaree).doubleClick().build().perform();
				break;
		}
		
		return new ProductsPage(driver);
	}
	
	public ProductsPage selectMenSubCategory(String subCategory)
	{
		a = new Actions(driver);
		switch (subCategory)
		{
			case "TSHIRTS":
				waitForElementToBeClickable(menTshirts);
				a.moveToElement(menTshirts).doubleClick().build().perform();
				break;
			case "JEANS":
				waitForElementToBeClickable(menJeans);
				a.moveToElement(menJeans).doubleClick().build().perform();
				break;
		}
		
		return new ProductsPage(driver);
	}
	
	public ProductsPage selectKidsSubCategory(String subCategory)
	{
		a = new Actions(driver);
		switch (subCategory)
		{
			case "DRESS":
				waitForElementToBeClickable(kidsDress);
				a.moveToElement(kidsDress).doubleClick().build().perform();
				break;
			case "TOPS & SHIRTS":
				waitForElementToBeClickable(kidsTopsShirts);
				a.moveToElement(kidsTopsShirts).doubleClick().build().perform();
				break;
		}
		
		return new ProductsPage(driver);
	}
	
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
