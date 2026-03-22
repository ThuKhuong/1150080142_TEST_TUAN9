package tests;

import base.BaseTest;
import data.UserDataProvider;
import models.UserData;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class UserLoginTest extends BaseTest implements ITest {

    private LoginPage loginPage;
    private String testName = "";

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(dataProvider = "userJsonData", dataProviderClass = UserDataProvider.class)
    public void testLoginFromJson(UserData userData) {
        testName = userData.getDescription();

        if (userData.isExpectSuccess()) {
            InventoryPage inventoryPage = loginPage.login(
                    userData.getUsername(),
                    userData.getPassword()
            );
            Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page is not loaded");
        } else {
            loginPage.loginExpectingFailure(
                    userData.getUsername(),
                    userData.getPassword()
            );
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed");
        }
    }

    @Override
    public String getTestName() {
        return testName;
    }
}