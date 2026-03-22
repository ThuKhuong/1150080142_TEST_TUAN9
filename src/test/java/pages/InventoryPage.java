package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(className = "shopping_cart_badge")
    private List<WebElement> cartBadge;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return inventoryList.isDisplayed();
    }

    public InventoryPage addFirstItemToCart() {
        addToCartButtons.get(0).click();
        return this;
    }

    public InventoryPage addItemByName(String name) {
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equals(name)) {
                addToCartButtons.get(i).click();
                break;
            }
        }
        return this;
    }

    public int getCartItemCount() {
        if (cartBadge.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(cartBadge.get(0).getText());
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage(driver);
    }
}