package Tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import Data.ConfigFileReader;
import Utilities.Helper;


public class TestBase {

	public static WebDriver driver;
	ConfigFileReader ConfigFileReader;

	@BeforeSuite
	@Parameters({"browser"})
	public void StartDriver(@Optional("chrome") String Browsername) {
		ConfigFileReader = new ConfigFileReader();
		
		//ignore case(capital & small) of the browser characters 
		if(Browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}
		else if(Browsername.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			FirefoxOptions options_firefox  = new FirefoxOptions();
			options_firefox.addArguments("-private");
			driver = new FirefoxDriver(options_firefox);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.navigate().to(ConfigFileReader.getApplicationUrl());

	}
	@AfterSuite
	public void StopDriver() {
		driver.quit();
	}

	//Take screenshot when test case fails
	@AfterMethod
	public void TakescreenshotOnFailure(ITestResult result) 
	{
		if(result.getStatus() == ITestResult.FAILURE) 
		{
			System.out.println("Failed");
			System.out.println("Take ScreenShot ....");
			Helper.CaptureScreenShot(driver, result.getName());
		}
	}

}
