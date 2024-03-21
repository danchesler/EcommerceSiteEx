package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YouTubePage extends PageUtilities{
	private WebDriver driver;
	
	public YouTubePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="#channel-header-container #channel-name #text")
	private WebElement channelName;
	
	
	public boolean isChannelNameDisplayed() {
		return channelName.isDisplayed();
	}
}
