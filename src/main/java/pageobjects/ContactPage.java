package pageobjects;

import java.io.IOException;

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
	
	@FindBy(xpath="//input[contains(@class,'submit_form')]")
	private WebElement submitForm;
	
	@FindBy(xpath="(//div[contains(@class,'alert-success')])[1]")
	private WebElement submitSuccess;
	
	@FindBy(xpath="//div[@id='form-section']/a/span")
	private WebElement homeBtn;
	
	public boolean getInTouchIsDisplayed() {
		return getInTouch.isDisplayed();
	}
	
	public void enterName(String name) {
		nameBox.sendKeys(name);
	}
	
	public void enterEmail(String email) {
		emailBox.sendKeys(email);
	}
	
	public void enterSubject(String subject) {
		subjectBox.sendKeys(subject);
	}
	
	public void enterMessage(String msg) {
		messageBox.sendKeys(msg);
	}
	
	public void submitForm() {
		submitForm.click();
	}

	public void acceptAlert() throws InterruptedException {
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert() throws InterruptedException {
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
	}
	
	
	public void uploadFile() throws IOException, InterruptedException {
		Actions a = new Actions(driver);
		a.moveToElement(chooseFile).click().build().perform();
		
		Thread.sleep(2000);
		String windowScript = "C:\\Users\\super\\eclipse-SeleniumProject\\automation-exercise\\resources\\fileupload.exe";
		Runtime.getRuntime().exec(windowScript);
		
		/*
		JavascriptExecutor ex = ((JavascriptExecutor)driver);
		ex.executeScript("window.document.getElementsByName('upload_file')[0].click();");
		*/
	}
	
	public String getSuccessMsg() {
		return submitSuccess.getText();
	}
	
	public HomePage goToHomePage() {
		homeBtn.click();
		
		return new HomePage(driver);
	}
}
