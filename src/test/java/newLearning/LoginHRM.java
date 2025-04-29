package newLearning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.asserts.Assertion;

import com.aventstack.chaintest.plugins.ChainTestListener;

import org.testng.annotations.*;

//@Listeners(ChainTestListener.class)
public class LoginHRM {
	WebDriver driver = new ChromeDriver();
	WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

	// For login page elements
	String usernameInputFieldXpath = "//input[@name = 'username' and @placeholder = 'Username']";
	String passwordInputFieldXpath = "//input[@type= 'password']";
	String loginButtonCTAXpath = "//button[@type= 'submit']";

	String expectedDashboardURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

	@Parameters({ "loginPageURL" })
	@Test(priority = 1)
	public void openBrowserAndRedirect(String urlRedirect) {
		driver.get(urlRedirect);
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	@DataProvider(name = "usernameAndPassword")
	Object[][] getUsernameAndPassword() {
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

		ChainTestListener.log("Extracted username and password is: " + username + " & " + password);
		// System.out.println(username + "\n" + password);

		Object[][] objects = new Object[1][2];
		objects[0][0] = username;
		objects[0][1] = password;

		return objects;
	}

	// @Parameters({ "username", "password" })
	@Test(priority = 3, dataProvider = "usernameAndPassword")
	void LoginIntoPage(String username, String password) {

		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(usernameInputFieldXpath)));
		ChainTestListener.log("Logging with fetched username: " + username + " & password: " + password);

		driver.findElement(By.xpath(usernameInputFieldXpath)).sendKeys(username);
		driver.findElement(By.xpath(passwordInputFieldXpath)).sendKeys(password);

		driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginButtonCTAXpath))).click();
	}

	@Test(priority = 4)
	void loginVerification() {
		// To verify the successful login with provided credentials
		Assertion assertion = new Assertion();

		// It will fail the test run if the match is not successful
		ChainTestListener.log("Verifying navigated URL with actual. Expected URL is:" + expectedDashboardURL
				+ " and actual URL is: " + driver.getCurrentUrl());
		assertion.assertEquals(driver.getCurrentUrl(), expectedDashboardURL);

	}

	@AfterClass
	void closeBrowser() throws InterruptedException {
		if (driver != null) {
			System.out.println(((ChromiumDriver) driver).getCapabilities().toString());
			Thread.sleep(1000);
			driver.quit();
		}
	}
}
