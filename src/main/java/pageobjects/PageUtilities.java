package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageUtilities {

	private WebDriver driver;
	private JavascriptExecutor js;
	
	public PageUtilities (WebDriver driver)
	{
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageURL()
	{
		return driver.getCurrentUrl();
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void goPrevPage()
	{
		driver.navigate().back();
	}
	
	public void scrollToElement(WebElement ele)
	{
		js.executeScript("arguments[0].scrollIntoView()", ele);
	}
	
	public void scrollDownABit()
	{
		js.executeScript("window.scrollBy(0,300)", "");
	}
	
	public int removeDollarFromPriceStr(WebElement priceEle)
	{
		String priceFullStr = priceEle.getText();
		String priceValueStr = priceFullStr.split(" ")[1];
		int price = Integer.parseInt(priceValueStr);
		
		return price;
	}
}