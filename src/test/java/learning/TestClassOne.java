package learning;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestClassOne {
    WebDriver driver = new ChromeDriver();

    @Test
    void openGoogle() {

        driver.get("https://www.google.co.in");
        driver.manage().window().maximize();

        driver.findElement(By.className("gLFyf")).sendKeys("Selenium", Keys.ENTER);
    }

//    @Test
//    void openAura() {
//
//        driver.get("https://www.auranft.co");
//        driver.manage().window().maximize();
//
//        driver.findElement(By.xpath("//*[@id=\"header-main-page\"]/header/div/div/div[1]/div[2]")).click();
//    }
}
