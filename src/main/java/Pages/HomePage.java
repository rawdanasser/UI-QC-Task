package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	By Title = By.cssSelector("span.title");
	By Menu = By.id("react-burger-menu-btn");
	By LogoutBtn = By.id("logout_sidebar_link");
	
	public String GetTitle() {
		return driver.findElement(Title).getText();
	}
	public void Logout() {
		driver.findElement(Menu).click();
		driver.findElement(LogoutBtn).click();
	}
}
