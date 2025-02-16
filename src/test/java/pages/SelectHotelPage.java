package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage {
    WebDriver driver;

    @FindBy(id = "radiobutton_0") // Select first hotel
    WebElement selectHotelRadio;

    @FindBy(id = "continue") // Continue button
    WebElement continueButton;

    public SelectHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFirstHotel() {
        selectHotelRadio.click();
        continueButton.click();
    }
}
