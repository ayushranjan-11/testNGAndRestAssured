package newLearning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;

public class LoginHRM {
	WebDriver driver = new ChromeDriver();
	WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@Parameters({ "loginPageURL" })
	@Test(priority = 1)
	public void openBrowserAndRedirect(String urlRedirect) {
		driver.get(urlRedirect);
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	void getUsernameAndPassword() {
		String usernameTextXpath = "//p[@class='oxd-text oxd-text--p'][1]";
		String passwordTextXpath = "//p[@class='oxd-text oxd-text--p'][2]";

		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(usernameTextXpath)));

		String username = driver.findElement(By.xpath(usernameTextXpath)).getText();
		String password = driver.findElement(By.xpath(passwordTextXpath)).getText();

		String[] usernameExtract = username.split(":");
		username = usernameExtract[1].trim();
//		System.out.println(username);

		String[] passwordExtract = password.split(":");
		password = passwordExtract[1].trim();

		System.out.println(username + "\n" + password);
	}

	@Parameters({ "username", "password" })
	@Test(priority = 3)
	void LoginIntoPage(String username, String password) {
		String usernameInputFieldXpath = "//input[@name = 'username' and @placeholder = 'Username']";
		String passwordInputFieldXpath = "//input[@type= 'password']";
		String loginButtonCTAXpath = "//button[@type= 'submit']";

		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(usernameInputFieldXpath)));

		driver.findElement(By.xpath(usernameInputFieldXpath)).sendKeys(username);
		driver.findElement(By.xpath(passwordInputFieldXpath)).sendKeys(password);

		driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginButtonCTAXpath))).click();
	}

	@AfterClass
	void closeBrowser() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(1000);
			driver.quit();
		}
	}
}
