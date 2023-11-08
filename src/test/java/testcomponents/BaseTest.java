package testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pageobjects.HomePage;

public class BaseTest {

	private WebDriver driver;
	private HomePage homepage;
	
	public WebDriver driverSetup() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\globalProperties.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ?  System.getProperty("browser") : prop.getProperty("browser");
		
		if (browserName.contains("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (browserName.contains("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (browserName.contains("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod (alwaysRun=true)
	public HomePage launchWebSite() throws IOException
	{
		driver = driverSetup();
		homepage = new HomePage(driver);
		homepage.goToHomepage();
		return homepage;
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		driver.quit();
	}
}
