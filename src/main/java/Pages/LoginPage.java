package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	By UserName = By.id("user-name");
	By Pass = By.id("password");
	By LoginBtn = By.id("login-button");
	By Error = By.cssSelector("div.error-message-container.error");
	
	public void LoginMethod(String name,String pass) {
		driver.findElement(UserName).sendKeys(name);
		driver.findElement(Pass).sendKeys(pass);
		
	}
	public void ClickLogin() {
		driver.findElement(LoginBtn).click();
	}
	public String GetErrorMessage() {
		return driver.findElement(Error).getText();
	}
	
}
