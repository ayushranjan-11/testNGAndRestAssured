package newLearning;

import org.openqa.selenium.By;
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
		String usernameTextXpath = "//p[@class='oxd-text oxd-text--p'][1]";
		String passwordTextXpath = "//p[@class='oxd-text oxd-text--p'][2]";
		
		String username = driver.findElement(By.xpath(usernameTextXpath)).getText();
		String password = driver.findElement(By.xpath(passwordTextXpath)).getText();
	}
}
