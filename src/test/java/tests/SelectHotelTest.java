package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.SearchHotelPage;
import pages.SelectHotelPage;

public class SelectHotelTest {
    WebDriver driver;
    LoginPage loginPage;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/index.php");

        loginPage = new LoginPage(driver);
        searchHotelPage = new SearchHotelPage(driver);
        selectHotelPage = new SelectHotelPage(driver);

        loginPage.login("karadipai", "Test@123");
        searchHotelPage.searchHotel("Sydney", "Hotel Creek", "Deluxe");
    }

    @Test(priority = 5)
    public void testSelectHotel() {
        selectHotelPage.selectFirstHotel();
        Assert.assertFalse(driver.getTitle().contains("Book Hotel"), "Selection Failed!");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
