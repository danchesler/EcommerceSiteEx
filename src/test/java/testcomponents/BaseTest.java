package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import pageobjects.HomePage;

public class BaseTest {

	private WebDriver driver;
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
			op.addExtensions(new File("C:\\Users\\super\\eclipse-SeleniumProject\\automation-exercise\\resources\\ublock.crx"));
			
			
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
		Thread.sleep(5000);
		driver.quit();
	}
}
