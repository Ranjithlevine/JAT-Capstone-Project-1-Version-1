package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // Avoid implicit wait conflicts
        driver.get("https://adactinhotelapp.com/index.php");

        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
    }

    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.login("karadipai", "Test@123");
        Assert.assertEquals(driver.getTitle(), "Adactin.com - Search Hotel", "Login Failed!");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        loginPage.login("Vibav", "1236");

        // Wait for error message with text
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.getErrorMessageLocator()));
        wait.until(ExpectedConditions.textToBePresentInElement(errorElement, "Invalid Login"));

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Invalid Login"), "Error message not displayed!");
    }

    @AfterMethod
    public void resetToLoginPage() {
        driver.manage().deleteAllCookies(); // Clear session data
        driver.navigate().to("https://adactinhotelapp.com/index.php"); // Go back to login page
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
