package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pageobjects.HomePage;

public class BaseTest extends TestUtilities {

	protected WebDriver driver;
	protected HomePage homepage;
	
	public WebDriver driverSetup() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\globalProperties.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ?  System.getProperty("browser") : prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addExtensions(new File(System.getProperty("user.dir") + "\\resources\\ublock.crx"));
		
			op.addArguments("--disable-save-password-bubble");
			Map<String, Object> prefs = new HashMap<String, Object>();
		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
		    
		    prefs.put("autofill.profile_enabled", false);
		    
		    op.setExperimentalOption("prefs", prefs);
			//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			
			driver = new ChromeDriver(op);
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeTest (alwaysRun=true)
	public HomePage launchWebSite() throws IOException, InterruptedException
	{
		driver = driverSetup();
		homepage = new HomePage(driver);
		homepage.goToHomepage();
		return homepage;
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToBeClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		//Thread.sleep(5000);
		//driver.quit();
	}
}
