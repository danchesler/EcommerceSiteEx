package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage extends PageCommon {

	WebDriver driver;
	
	public TestCasesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h2 b")
	private WebElement testCasesHeader;
	
	@FindBy(xpath="//h4/a/u")
	private List<WebElement> testCasesList;
	
	@FindBy(xpath="//h4/a/u/parent::a/parent::h4/parent::div/following-sibling::div[@class='panel-collapse in']")
	private List<WebElement> collapseExpanded;
	
	@FindBy(xpath="//h4/a/u/parent::a/parent::h4/parent::div/following-sibling::div[@class='panel-collapse collapse']")
	private List<WebElement> collapseClosed;
	
	public String getTestCasesPgHeader() {
		return testCasesHeader.getText();
	}
	
	public int getNumerOfTestCases() {
		return testCasesList.size();
	}
	
	public void openTestCase(int index) throws InterruptedException {
		//WebElement collapse = driver.findElement(By.cssSelector("#collapse" + Integer.toString(index+1)));
		//String collapseClass = collapse.getAttribute("class");
		//System.out.println(collapseClass);
		
		//waitForWebElementToAppear(collapseClosed.get(index));
		Thread.sleep(1000);
		testCasesList.get(index).click();
	}
	
	public void closeTestCase(int index) throws InterruptedException {
		//WebElement collapse = driver.findElement(By.cssSelector("#collapse" + Integer.toString(index+1)));
		//String collapseClass = collapse.getAttribute("class");
		//System.out.println(collapseClass);

		//waitForWebElementToAppear(collapseExpanded.get(0));
		
		Thread.sleep(1000);
		testCasesList.get(index).click();
	}

	

}
