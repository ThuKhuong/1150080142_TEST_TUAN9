package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage fillCheckoutForm(Map<String, String> data) {
        firstNameField.clear();
        firstNameField.sendKeys(data.get("firstName"));

        lastNameField.clear();
        lastNameField.sendKeys(data.get("lastName"));

        postalCodeField.clear();
        postalCodeField.sendKeys(data.get("postalCode"));

        return this;
    }

    public void clickContinue() {
        continueButton.click();
    }
}