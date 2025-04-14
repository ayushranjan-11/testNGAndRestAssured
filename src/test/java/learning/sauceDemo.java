package learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class sauceDemo {
	WebDriver driver;
	String expectedURLAfterLogin = "https://www.saucedemo.com/inventory.html";
	SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	@Parameters("browserSelected")
	void browserSelection(@Optional("CHROME") String browserChoice) {

		switch (browserChoice.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Selected browser is not available");
		}

	}
// @BeforeSuite driver initialization is helpful when single test under suit will run, for multiple tests
// under one suite null pointer exception was thrown

	@Parameters("url")
	@BeforeClass
	void LaunchWebsite(String websiteUrl) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.get(websiteUrl);
		driver.manage().window().maximize();
	}

	@AfterTest
	void browserClose() {
		driver.quit();
		softAssert.assertAll();
	}

	@Test
	void provideLoginCredentialsAndLogin() {
		// Finding and storing username from website itself as provided
		WebElement loginUsername1 = driver.findElement(By.id("login_credentials"));
		String divText = loginUsername1.getText();

		// to go to 2nd line
		String[] usernames = divText.split("\\r?\\n");

		// to get index base element
		if (usernames.length > 1) {
			// System.out.println(usernames[1]);
			driver.findElement(By.id("user-name")).sendKeys(usernames[1]); // Username
		} else {
			System.out.println("not text found");
		}

		// Finding and storing password from website itself as provided
		WebElement loginPassword = driver.findElement(By.className("login_password"));
		String passwordTexts = loginPassword.getText();

		String[] password = passwordTexts.split("\\r?\\n");
		if (password.length > 1) {
			// System.out.println(password[1]);
			driver.findElement(By.id("password")).sendKeys(password[1]); // Password
		} else {
			System.out.println("not text found");
		}

		// Login button press
		driver.findElement(By.id("login-button")).click();
	}

	@Test
	void verifySuccessfulLogin() {
		String loginVerifyUrl = driver.getCurrentUrl();
		softAssert.assertEquals(loginVerifyUrl, expectedURLAfterLogin);
	}

	@Test
	String verifySuccessfulLoginWithReturn() {
		String loginVerifyUrl = driver.getCurrentUrl();
		System.out.println("Link from return method " + loginVerifyUrl);
		// softAssert.assertEquals(loginVerifyUrl, expectedAfterLoginURL);

		return loginVerifyUrl;

		// Running this method through TestNG will be ignored because of the return type
		// is not void
		// If needed to run this method also, then XML file should have
		// allow-return-values="true"
	}

}
