package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        ConfigReader.initProperties();
        String url = ConfigReader.get("baseUrl");
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(page);
        Assert.assertTrue(dashboardPage.isAtDashboard(), "Dashboard not loaded after login.");
    }
}
