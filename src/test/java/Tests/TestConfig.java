package Tests;

import org.testng.annotations.BeforeMethod;

import Pages.HomePage;
import Pages.LoginPage;

public class TestConfig extends TestBase{

	
	LoginPage LoginObject;
	HomePage HomeObject;
	
	@BeforeMethod
	public void Declaration() {
		LoginObject = new LoginPage(driver);
		HomeObject = new HomePage(driver);
	}
}
