package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageobjects.HomePage;

public class BaseTest extends TestUtilities {
//test comment2
	public WebDriver driver;
	protected HomePage homepage;
	
	public WebDriver driverSetup() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\globalProperties.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ?  System.getProperty("browser") : prop.getProperty("browser");
		
		if (browserName.contains("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addExtensions(new File(System.getProperty("user.dir") + "\\resources\\ublock.crx"));
			
			if (browserName.contains("headless")) {
				op.addArguments("headless");
			}
			
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", System.getProperty("user.dir"));
		    op.setExperimentalOption("prefs", prefs);
			
			driver = new ChromeDriver(op);
			
			if (browserName.contains("headless")) {
				driver.manage().window().setSize(new Dimension(1920,1080));
			}
			
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
	
	
	@AfterTest (alwaysRun=true)
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}
}
