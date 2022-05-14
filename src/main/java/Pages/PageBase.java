package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class PageBase {

	protected WebDriver driver;
	public Actions action;
	
	
	public PageBase(WebDriver driver) {
		this.driver = driver;
	}

}
