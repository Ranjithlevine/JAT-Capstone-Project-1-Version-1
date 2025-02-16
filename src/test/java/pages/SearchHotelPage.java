package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage {
    WebDriver driver;

    @FindBy(id = "location")
    WebElement location;

    @FindBy(id = "hotels")
    WebElement hotels;

    @FindBy(id = "room_type")
    WebElement roomType;

    @FindBy(id = "Submit")
    WebElement searchButton;

    @FindBy(id = "location_span") // Error message element for location
    WebElement errorMessage;

    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchHotel(String loc, String hotel, String room) {
        location.sendKeys(loc);
        hotels.sendKeys(hotel);
        roomType.sendKeys(room);
        searchButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
