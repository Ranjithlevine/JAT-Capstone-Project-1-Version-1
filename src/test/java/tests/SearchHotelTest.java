package tests;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import pages.SearchHotelPage;

import java.time.Duration;

public class SearchHotelTest {
    WebDriver driver;
    LoginPage loginPage;
    SearchHotelPage searchHotelPage;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/index.php");

        loginPage = new LoginPage(driver);
        searchHotelPage = new SearchHotelPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage.login("karadipai", "Test@123");
        System.out.println("Login successful!");
    }

    @Test(priority = 3)
    public void testValidHotelSearch() {
        System.out.println("Navigating to Search Hotel page...");
        driver.get("https://adactinhotelapp.com/SearchHotel.php");  // Ensure correct page

        WebElement locationDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location")));
        System.out.println("Location dropdown is now visible!");
        locationDropdown.click();

        searchHotelPage.searchHotel("Sydney", "Hotel Creek", "Deluxe");
        Assert.assertTrue(driver.getTitle().contains("Select Hotel"), "Search Failed!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
