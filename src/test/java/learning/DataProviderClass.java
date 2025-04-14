package learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

//This class should be run through it's xml file
public class DataProviderClass {
	WebDriver driver;

	@Parameters({ "url" })
	@BeforeMethod
	void launchWebsite(String websiteUrl) {
		driver = new ChromeDriver();
		driver.get(websiteUrl);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "loginDataProvider")
	void loginToWebsite(String username, String password) throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys(username); // username
		driver.findElement(By.id("password")).sendKeys(password); // password

		Thread.sleep(2000);
		driver.findElement(By.id("login-button")).click();

//        driver.quit();
	}

	@DataProvider(name = "loginDataProvider")
	Object[][] dataProviderClass() {
		Object[][] objects = new Object[3][2];
		objects[0][0] = "standard_user";
		objects[0][1] = "secret_sauce";

		objects[1][0] = "locked_out_user";
		objects[1][1] = "secret_sauce";

		objects[2][0] = "problem_user";
		objects[2][1] = "secret_sauce";

		return objects;
	}

	@AfterMethod
	void browserQuit() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
