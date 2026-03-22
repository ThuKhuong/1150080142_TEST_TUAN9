package tests;

import base.BaseTest;
import data.LoginDataProvider;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.Map;

public class LoginDataDrivenTest extends BaseTest implements ITest {

    private LoginPage loginPage;
    private String testName = "";

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = {"smoke", "regression"})
    public void testLoginFromExcel(Map<String, String> data) {
        testName = data.get("description");

        String username = data.get("username");
        String password = data.get("password");
        String expectedUrl = data.get("expected_url");
        String expectedError = data.get("expected_error");

        boolean isPositiveCase = expectedUrl != null && !expectedUrl.isEmpty();

        if (isPositiveCase) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page is not loaded");
            Assert.assertEquals(getDriver().getCurrentUrl(), expectedUrl, "Current URL is incorrect");
        } else {
            loginPage.loginExpectingFailure(username, password);
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed");
            Assert.assertEquals(loginPage.getErrorMessage(), expectedError, "Error message is incorrect");
        }
    }

    @Override
    public String getTestName() {
        return testName;
    }
}