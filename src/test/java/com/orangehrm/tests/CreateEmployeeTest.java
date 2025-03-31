package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.annotations.Test;

public class CreateEmployeeTest extends BaseTest {

    @Test
    public void testCreateEmployee() {
        ConfigReader.initProperties();
        String url = ConfigReader.get("baseUrl");
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(page);
        dashboardPage.loginVerification();
        dashboardPage.navigateToTab("PIM");

        PIMPage pimPage = new PIMPage(page);
        pimPage.navigateToCreateEmployeeForm();
        pimPage.createEmployee();

        dashboardPage.logout();
        loginPage.logoutVerification();
    }
}
