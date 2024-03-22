package pageobjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YouTubePage extends PageUtilities{
	WebDriver driver;
	
	public YouTubePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="#channel-header-container #channel-name #text")
	private WebElement channelName;
	
	
	public boolean isChannelNameDisplayed() {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOf(channelName))
			.isDisplayed();	
	}
}
