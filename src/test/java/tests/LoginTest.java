package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginSuccess() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page is not loaded");
    }

    @Test
    public void loginFailWrongPassword() {
        loginPage.loginExpectingFailure("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed");
    }
}