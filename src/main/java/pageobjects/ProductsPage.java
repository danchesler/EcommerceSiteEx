package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends PageCommon {

	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".title")
	private WebElement header;
	
	@FindBy(linkText="View Product")
	private List<WebElement> viewProducts;
	
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
	
	
	public String getProductsHeaderText()
	{
		return header.getText();
	}

	public ProductDetailsPage viewProductByIndex(int i)
	{
		viewProducts.get(i).click();
		
		return new ProductDetailsPage(driver);
	}
	
	
}
