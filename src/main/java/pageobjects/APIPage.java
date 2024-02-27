package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class APIPage extends PageCommon {
	WebDriver driver;
	
	public APIPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2/b")
	WebElement pageTitle;
	
	@FindBy(css="h4 u")
	List<WebElement> apiCasesList;
	
	public String getAPIPageTitle() {
		return pageTitle.getText();
	}
	
	public int getAmountofAPIcases() {
		return apiCasesList.size();
	}
	
	public void openThenCloseAPICase(int i) throws InterruptedException {
		apiCasesList.get(i).click();
		Thread.sleep(2000);
		apiCasesList.get(i).click();
	}
}
