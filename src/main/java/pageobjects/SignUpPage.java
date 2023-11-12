package pageobjects;

import java.util.HashMap;

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
	
	@FindBy(css=".login-form h2")
	private WebElement loginHeader;
	
	@FindBy(css="input[data-qa='login-email']")
	private WebElement emailLogin;
	
	@FindBy(css="input[data-qa='login-password']")
	private WebElement passwordLogin;
	
	@FindBy(css=".login-form p")
	private WebElement wrongLogin;
	
	@FindBy(css="button[data-qa*='login']")
	private WebElement loginButton;
	
	@FindBy(css=".signup-form h2")
	private WebElement signupHeader;
	
	@FindBy(css="input[data-qa*='name']")
	private WebElement nameEditBox1;
	
	@FindBy(css="input[data-qa='signup-email']")
	private WebElement emailEditBox;
	
	@FindBy(css=".signup-form p")
	private WebElement emailExists;
	
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
	
	public WebElement getLoginHeaderEle()
	{
		return loginHeader;
	}
	
	public void enterExistingUserInfo(String email, String pw)
	{
		emailLogin.sendKeys(email);
		passwordLogin.sendKeys(pw);
	}
	
	public String getIncorrectLoginText()
	{
		return wrongLogin.getText();
	}
	
	public HomePage login()
	{
		loginButton.click();
		return new HomePage(driver);
	}
	
	public WebElement getSignupHeaderEle()
	{
		return signupHeader;
	}
	
	public void enterNewUserInfo(String name, String email)
	{
		nameEditBox1.sendKeys(name);
		emailEditBox.sendKeys(email);
	}
	
	public String getEmailExistsText()
	{
		return emailExists.getText();
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
	
	public void enterSignUpDetails(SignUpPage sp, HashMap<String, String> data)
	{
		sp.selectTitle(data.get("title"));
		sp.enterName(data.get("username2"));
		sp.enterPassword(data.get("password"));
		sp.enterDateOfBirth(Integer.parseInt(data.get("day")), data.get("month"), data.get("year"));
		sp.joinNewsletter();
		sp.receiveSpecialOffers();
		sp.enterFirstName(data.get("firstname"));
		sp.enterLastName(data.get("lastname"));
		sp.enterCompany(data.get("company"));
		sp.enterAddress1(data.get("address"));
		sp.enterAddress2(data.get("address2"));
		sp.enterCountry(data.get("country"));
		sp.enterState(data.get("state"));
		sp.enterCity(data.get("city"));
		sp.enterZipcode(data.get("zipcode"));
		sp.enterMobileNumber(data.get("mobilenumber"));
	}
	
	public AccountCreatedPage createAccount()
	{
		createAccount.click();
		
		return new AccountCreatedPage(driver);
	}
	
}
