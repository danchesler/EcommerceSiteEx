package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends ProductsPage {

	WebDriver driver;
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".product-information h2")
	private WebElement productName;
	
	@FindBy(xpath="(//div[@class='product-information']/p)[1]")
	private WebElement category;
		
	@FindBy(xpath="//div[@class='product-information']/span/span")
	private WebElement price;
	
	@FindBy(xpath="(//div[@class='product-information']/p/b)[1]")
	private WebElement availability;
	
	@FindBy(xpath="(//div[@class='product-information']/p/b)[2]")
	private WebElement condition;
	
	@FindBy(xpath="(//div[@class='product-information']/p/b)[3]")
	private WebElement brand;

	
	public boolean isCategoryDisplayed()
	{
		return category.isDisplayed();
	}
	
	public String getCategory()
	{
		return category.getText();
	}
	
	public boolean isPriceDisplayed()
	{
		return price.isDisplayed();
	}
	
	public boolean isAvailabilityDisplayed()
	{
		return availability.isDisplayed();
	}
	
	public boolean isConditionDisplayed()
	{
		return condition.isDisplayed();
	}
	
	public boolean isBrandDisplayed()
	{
		return brand.isDisplayed();
	}
	
}
