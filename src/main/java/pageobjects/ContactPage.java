package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends PageCommon {

	WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='contact-form']/h2")
	private WebElement getInTouch;
	
	@FindBy(xpath="//input[@name='name']")
	private WebElement nameBox;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailBox;
	
	@FindBy(xpath="//input[@name='subject']")
	private WebElement subjectBox;
	
	@FindBy(xpath="//textarea[@name='message']")
	private WebElement messageBox;
	
	@FindBy(xpath="//input[@name='upload_file']")
	private WebElement chooseFile;
	
	public boolean getInTouchIsDisplayed()
	{
		return getInTouch.isDisplayed();
	}
	
	public void enterName(String name)
	{
		nameBox.sendKeys(name);
	}
	
	public void enterEmail(String email)
	{
		emailBox.sendKeys(email);
	}
	
	public void enterSubject(String subject)
	{
		subjectBox.sendKeys(subject);
	}
	
	public void enterMessage(String msg)
	{
		messageBox.sendKeys(msg);
	}
	
	public WebElement getChooseFileEle()
	{
		return chooseFile;
	}
	
	public void uploadFile()
	{
		Actions a = new Actions(driver);
		a.moveToElement(chooseFile).click().build().perform();
		
		/*
		JavascriptExecutor ex = ((JavascriptExecutor)driver);
		ex.executeScript("window.document.getElementsByName('upload_file')[0].click();");
		*/

	}
}
