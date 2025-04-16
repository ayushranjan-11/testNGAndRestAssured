package usingConfigFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UrlNavigationFromConfigFile {

	WebDriver driver = new ChromeDriver();
	WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

	Properties config = new Properties();

	public static void main(String[] args) throws IOException {
		UrlNavigationFromConfigFile urlNavigationFromConfigFile = new UrlNavigationFromConfigFile();
		urlNavigationFromConfigFile.urlNavigation();
	}

	void urlNavigation() throws IOException {
		// Get url from config file and pass for webdriver to navigate to it
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + File.separator + "src/main/resources/config.properties");
		config.load(fileInputStream);
		driver.get(config.getProperty("urlNew"));
		driver.manage().window().maximize();
	}

}
