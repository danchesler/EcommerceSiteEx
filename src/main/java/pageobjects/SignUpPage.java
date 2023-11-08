package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage extends PageCommon {

	WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Initial Login/Signup locators
	
	@FindBy(css=".signup-form h2")
	private WebElement signupHeader;

	@FindBy(css="input[data-qa*='name']")
	private WebElement nameEditBox1;
	
	@FindBy(css="input[data-qa='signup-email']")
	private WebElement emailEditBox;
	
	@FindBy(css="button[data-qa*='signup']")
	private WebElement signupButton;
	
	//Sign up page locators
	
	@FindBy(xpath="//div/h2/b")
	private WebElement enterAccountInfoHeader;
	
	@FindBy(id="id_gender1")
	private WebElement mrRadiobtn;
	
	@FindBy(id="id_gender2")
	private WebElement mrsRadiobtn;
	
	@FindBy(id="name")
	private WebElement nameEditBox2;
	
	@FindBy(id="password")
	private WebElement passwordEditBox;
	
	@FindBy(id="days")
	private WebElement dobDay;
	
	@FindBy(id="months")
	private WebElement dobMonth;
	
	@FindBy(id="years")
	private WebElement dobYear;
	
	@FindBy(id="newsletter")
	private WebElement newsletter;
	
	@FindBy(id="optin")
	private WebElement specialOffers;
	
	@FindBy(name="first_name")
	private WebElement firstName;
	
	@FindBy(name="last_name")
	private WebElement lastName;
	
	@FindBy(name="company")
	private WebElement company;
	
	@FindBy(name="address1")
	private WebElement address1;
	
	@FindBy(name="address2")
	private WebElement address2;
	
	@FindBy(name="country")
	private WebElement country;
	
	@FindBy(id="state")
	private WebElement state;
	
	@FindBy(id="city")
	private WebElement city;
	
	@FindBy(id="zipcode")
	private WebElement zipcode;
	
	@FindBy(id="mobile_number")
	private WebElement mobileNumber;
	
	@FindBy(css="[data-qa*='create']")
	private WebElement createAccount;
	
	//Initial login/signup methods
	
	public WebElement getSignupHeaderElement()
	{
		return signupHeader;
	}
	
	public void enterNewUserInfo(String name, String email)
	{
		nameEditBox1.sendKeys(name);
		emailEditBox.sendKeys(email);
	}
	
	public void submitNewUser()
	{
		signupButton.click();
	}
	
	
	//Sign up page methods
	
	public WebElement getEnterAccountInfoHeader()
	{
		return enterAccountInfoHeader;
	}

	public void selectTitle (String title)
	{
		if (title.equalsIgnoreCase("mr."))
			mrRadiobtn.click();
		else if (title.equalsIgnoreCase("mrs."))
			mrsRadiobtn.click();
	}
	
	public void enterName(String name)
	{
		nameEditBox2.clear();
		nameEditBox2.sendKeys(name);
	}
	
	public void enterPassword(String pw)
	{
		passwordEditBox.sendKeys(pw);
	}
	
	public void enterDateOfBirth(int day, String month, String year)
	{
		Select dayDropdown = new Select(dobDay);
		dayDropdown.selectByIndex(day);
		
		Select monthDropdown = new Select(dobMonth);
		monthDropdown.selectByVisibleText(month);
		
		Select yearDropdown = new Select(dobYear);
		yearDropdown.selectByValue(year);
		
	}
	
	public void joinNewsletter()
	{
		newsletter.click();
	}
	
	public void receiveSpecialOffers()
	{
		specialOffers.click();
	}
	
	public void enterFirstName(String fn)
	{
		firstName.sendKeys(fn);
	}
	
	public void enterLastName(String ln)
	{
		lastName.sendKeys(ln);
	}
	
	public void enterCompany(String c)
	{
		company.sendKeys(c);
	}
	
	public void enterAddress1(String addr1)
	{
		address1.sendKeys(addr1);
	}
	
	public void enterAddress2(String addr2)
	{
		address2.sendKeys(addr2);
	}
	
	public void enterCountry(String c)
	{
		country.sendKeys(c);
	}
	
	public void enterState(String s)
	{
		state.sendKeys(s);
	}
	
	public void enterCity(String c)
	{
		city.sendKeys(c);
	}
	
	public void enterZipcode(String zip)
	{
		zipcode.sendKeys(zip);
	}
	
	public void enterMobileNumber(String num)
	{
		mobileNumber.sendKeys(num);
	}
	
	public AccountCreatedPage createAccount()
	{
		createAccount.click();
		
		return new AccountCreatedPage(driver);
	}
	
}
