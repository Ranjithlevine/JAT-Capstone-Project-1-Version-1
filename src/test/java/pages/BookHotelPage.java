package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class BookHotelPage {
    WebDriver driver;

    @FindBy(id = "first_name")
    WebElement firstNameField;

    @FindBy(id = "last_name")
    WebElement lastNameField;

    @FindBy(id = "address")
    WebElement billingAddressField;

    @FindBy(id = "cc_num")
    WebElement creditCardNumberField;

    @FindBy(id = "cc_type")
    WebElement creditCardTypeDropdown;

    @FindBy(id = "cc_exp_month")
    WebElement expiryMonthDropdown;

    @FindBy(id = "cc_exp_year")
    WebElement expiryYearDropdown;

    @FindBy(id = "cc_cvv")
    WebElement cvvNumberField;

    @FindBy(id = "book_now")
    WebElement bookNowButton;

    @FindBy(xpath = "//*[contains(@class, 'auth_error')]")  // Update this with the actual error message locator
    WebElement errorMessage;

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void bookRoom(String firstName, String lastName, String billingAddress,
                         String creditCardNumber, String creditCardType,
                         String expiryMonth, String expiryYear, String cvv) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        billingAddressField.sendKeys(billingAddress);
        creditCardNumberField.sendKeys(creditCardNumber);
        creditCardTypeDropdown.sendKeys(creditCardType);
        expiryMonthDropdown.sendKeys(expiryMonth);
        expiryYearDropdown.sendKeys(expiryYear);
        cvvNumberField.sendKeys(cvv);
        bookNowButton.click();
    }


    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'auth_error')]")));
            return errorMessage.getText();
        } catch (TimeoutException e) {
            return "Error message not found!";
        }
    }

}
