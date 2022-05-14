package Utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {


	//Method to take screenshot when the test fails
	//We use static because we don't need to create object we just need to call the method only
	public static void CaptureScreenShot(WebDriver driver,String screenshotname) {
		
		Path dest = Paths.get("./ScreenShots",screenshotname+".png");
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		    out.close();
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot"+e.getMessage());
		}
	}
}
