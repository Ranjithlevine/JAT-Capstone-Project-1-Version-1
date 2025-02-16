package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.SearchHotelPage;
import pages.SelectHotelPage;
import pages.BookHotelPage;

public class BookHotelTest {
    WebDriver driver;
    LoginPage loginPage;
    SearchHotelPage searchHotelPage;
    SelectHotelPage selectHotelPage;
    BookHotelPage bookHotelPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/index.php");

        loginPage = new LoginPage(driver);
        searchHotelPage = new SearchHotelPage(driver);
        selectHotelPage = new SelectHotelPage(driver);
        bookHotelPage = new BookHotelPage(driver);

        loginPage.login("karadipai", "Test@123");
        searchHotelPage.searchHotel("Sydney", "Hotel Creek", "Deluxe");
        selectHotelPage.selectFirstHotel();
    }

    @Test(priority = 6)
    public void testValidBooking() {
        bookHotelPage.bookRoom("John", "Doe", "123 Street, NY", "4111111111111111", "VISA", "12", "2027", "123");
        Assert.assertFalse(driver.getTitle().contains("Booking Confirmation"), "Booking Failed!");
    }

    @Test(priority = 7)
    public void testInvalidCardBooking() {
        bookHotelPage.bookRoom("John", "Doe", "123 Street, NY", "123456", "VISA", "12", "2023", "123");
        String errorMsg = bookHotelPage.getErrorMessage();
        Assert.assertFalse(errorMsg.contains("Invalid Card"), "Error message not displayed!");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
