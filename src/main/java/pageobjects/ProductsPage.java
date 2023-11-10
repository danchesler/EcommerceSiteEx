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
	
	@FindBy(id="search_product")
	private WebElement searchBar;
	
	@FindBy(id="submit_search")
	private WebElement searchBtn;
	
	@FindBy(css=".productinfo p")
	private List<WebElement> productNames;
	
	//product details locators
	
	
	public String getProductsHeaderText()
	{
		return header.getText();
	}

	public List<WebElement> getProductList()
	{
		return viewProducts;
	}
	
	public List<WebElement> getProductNames()
	{
		return productNames;
	}
	
	public ProductDetailsPage viewProductByIndex(int i)
	{
		viewProducts.get(i).click();
		
		return new ProductDetailsPage(driver);
	}
	
	public void searchProduct(String product)
	{
		searchBar.sendKeys(product);
		searchBtn.click();
	}
	

}
