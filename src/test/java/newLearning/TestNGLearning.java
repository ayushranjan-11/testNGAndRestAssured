package newLearning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class TestNGLearning {

	WebDriver driver;

	public static void main(String[] args) {
		System.out.println("Main method");
		methodTwo();

	}

	@BeforeClass
	public void methodBeforeClass() {
		System.out.println("Method called with annotation @BeforeClass");
	}

	@Test
	public void methodOne() {
		System.out.println("Method called with annotation @Test");
	}

	public static void methodTwo() {
		System.out.println("Method without annotations");
	}

	@Parameters({ "webURL" })
	@Test(priority = 1)
	public void chromeBrowserOpen(String visitURL) {
		driver = new ChromeDriver();
		driver.get(visitURL);
		driver.manage().window().maximize();
	}

	@Parameters({ "webURL" })
	@Test(priority = 2)
	public void firefoxBrowserOpen(String visitURL) {
		driver = new FirefoxDriver();
		driver.get(visitURL);
		driver.manage().window().maximize();
	}

	@Parameters({ "webURL" })
	@Test(priority = 3)
	public void edgeBrowserOpen(String visitURL) {
		driver = new EdgeDriver();
		driver.get(visitURL);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
