package Tests;

import static org.testng.Assert.assertEquals;
import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


public class LoginTest extends TestConfig {
     
	String CSV_File;                                                           //Create a string to store the csv path
	CSVReader reader;                                                          //Create object to read the csv data
	String[] csvcell;                                                          //Create array to save the csv cells data
	
	//In this case the user will login with valid credentials then verify the page title
	@Test(priority = 1)
	public void Login_With_Valid_Credentials() throws CsvValidationException, IOException {
		
		CSV_File = System.getProperty("user.dir")+"/src/test/java/Data/ValidData.csv";
		
		//while loop will be executed till the last value in csv file
		reader = new CSVReader(new FileReader(CSV_File));                      //Create a new object reads data from the csv file
		while((csvcell= reader.readNext()) != null)                            //Create a while loop to read all the csv cells data
		{
			String name = csvcell[0];
			String pass = csvcell[1];
			String title = csvcell[2];
			
			LoginObject.LoginMethod(name, pass);                                //Call login method to fill the user credentials
			LoginObject.ClickLogin();                                           //Call a method to click on the login button
			assertEquals(title,HomeObject.GetTitle());                          //Verify the home page title
			HomeObject.Logout();                                                //Call a method to click on the logout button

		}
	}
	/**********************************************************************************************************/
	/*In this case the user will login with invalid credentials then verify the error message
	 * Test the case sensitive of the password
	 * Test adding invalid username
	 * Test adding invalid password
	 */
	@Test(priority = 2)
	public void Login_With_InValid_Credentials() throws CsvValidationException, IOException {
		
		CSV_File = System.getProperty("user.dir")+"/src/test/java/Data/InvalidData.csv";
		
		//while loop will be executed till the last value in csv file
		reader = new CSVReader(new FileReader(CSV_File));
		while((csvcell= reader.readNext()) != null)
		{
			String name = csvcell[0];
			String pass = csvcell[1];
			String error = csvcell[2];
			
			LoginObject.LoginMethod(name, pass);
			LoginObject.ClickLogin();
			assertEquals(error,LoginObject.GetErrorMessage());
			driver.navigate().refresh();                                         //Refresh the browser to fill the new data

		}
		
	}
	/**********************************************************************************************************/
	//In this case the user will login with empty credentials then verify the error message
	@Test(priority = 3)
	public void Login_With_empty_Fields() throws CsvValidationException, IOException {
		
		CSV_File = System.getProperty("user.dir")+"/src/test/java/Data/EmptyFields.csv";
		
		//while loop will be executed till the last value in csv file
		reader = new CSVReader(new FileReader(CSV_File));
		while((csvcell= reader.readNext()) != null)
		{
			String name = csvcell[0];
			String pass = csvcell[1];
			String error = csvcell[2];
			
			LoginObject.LoginMethod(name, pass);
			LoginObject.ClickLogin();
			assertEquals(error,LoginObject.GetErrorMessage());
			driver.navigate().refresh();

		}
		
	}
	/**********************************************************************************************************/
	//In this case the user will click on the login buttion with empty fields then verify the error message
	@Test(priority = 4)
	public void Login_Without_Credentials() {
		LoginObject.ClickLogin();
		assertEquals("Epic sadface: Username is required",LoginObject.GetErrorMessage());
	}	
	
}
