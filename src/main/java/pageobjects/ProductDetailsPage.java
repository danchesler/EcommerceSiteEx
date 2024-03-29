package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends ProductsPage {

	WebDriver driver;
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".product-information h2")
	private WebElement productName;
	
	@FindBy(id="quantity")
	private WebElement quantity;
	
	@FindBy(css="button.cart")
	private WebElement addToCart;
	
	@FindBy(xpath="(//div[@class='product-information']/p)[1]")
	private WebElement category;
		
	@FindBy(xpath="//div[@class='product-information']/span/span")
	private WebElement price;
	
	@FindBy(xpath="(//div[@class='product-information']/p/b)[1]")
	private WebElement availability;
	
	@FindBy(xpath="(//div[@class='product-information']/p/b)[2]")
	private WebElement condition;
	
	@FindBy(xpath="(//div[@class='product-information']/p/b)[3]")
	private WebElement brand;

	@FindBy(css="[href*='reviews']")
	private WebElement writeYourReview;
	
	@FindBy(id="name")
	private WebElement reviewName;
	
	@FindBy(id="email")
	private WebElement reviewEmail;
	
	@FindBy(id="review")
	private WebElement review;
	
	@FindBy(id="button-review")
	private WebElement submitReview;
	
	@FindBy(css=".alert-success span")
	private WebElement reviewSuccess;
	
	public boolean isCategoryDisplayed() {
		return category.isDisplayed();
	}
	
	public String getCategory() {
		return category.getText();
	}
	
	public boolean isPriceDisplayed() {
		return price.isDisplayed();
	}
	
	public void changeQuantity(int q) {
		quantity.clear();
		quantity.sendKeys(Integer.toString(q));
	}
	
	public void addToCart() {
		addToCart.click();
	}
	
	public boolean isAvailabilityDisplayed() {
		return availability.isDisplayed();
	}
	
	public boolean isConditionDisplayed() {
		return condition.isDisplayed();
	}
	
	public boolean isBrandDisplayed() {
		return brand.isDisplayed();
	}
	
	public String getWriteYourReviewText() {
		return writeYourReview.getText();
	}
	
	public void enterReviewName(String name) {
		reviewName.sendKeys(name);
	}
	
	public void enterReviewEmail(String email) {
		reviewEmail.sendKeys(email);
	}
	
	public void enterReview(String r) {
		review.sendKeys(r);
	}
	
	public void submitReview() {
		submitReview.click();
	}
	
	public String getReviewSuccessText() {
		return reviewSuccess.getText();
	}
}
