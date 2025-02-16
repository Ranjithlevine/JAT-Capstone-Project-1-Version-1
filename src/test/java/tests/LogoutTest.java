package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.LogoutPage;

public class LogoutTest {
    WebDriver driver;
    LoginPage loginPage;
    LogoutPage logoutPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/index.php");

        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);

        loginPage.login("karadipai", "Test@123");
    }

    @Test(priority = 8)
    public void testLogout() {
        logoutPage.logout();
        Assert.assertEquals(driver.getTitle(), "Adactin.com - Logout", "Logout Failed!");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
