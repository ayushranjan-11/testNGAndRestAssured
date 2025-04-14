package newLearning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;

public class LoginHRM {
	WebDriver driver = new ChromeDriver();

	@Parameters({ "loginPageURL" })
	@Test(priority = 1)
	public void openBrowserAndRedirect(String loginURL) {
		driver.get(loginURL);
		driver.manage().window().maximize();
	}

	void getUsernameAndPassword() {

	}
}
