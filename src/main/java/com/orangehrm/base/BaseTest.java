package com.orangehrm.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orangehrm.utils.ExtentReportManager;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeClass
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeMethod
    public void createPage(Method method) {
        page = browser.newPage();
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }

        if (page != null) page.close();
    }

    @AfterClass
    public void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
