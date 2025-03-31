package com.orangehrm.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orangehrm.utils.ExtentReportManager;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

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
        page.setViewportSize(1920, 1080);
        test = extent.createTest(method.getName());  // Create a test with the method name
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            captureScreenshot(result);
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

    private void captureScreenshot(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotFolder = "test-output/screenshots/";
            String screenshotPath = screenshotFolder + result.getName() + "-" + System.currentTimeMillis() + ".png";

            java.nio.file.Files.createDirectories(Paths.get(screenshotFolder));

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

            String relativeScreenshotPath = screenshotPath.replace("test-output/", "");

            test.addScreenCaptureFromPath(relativeScreenshotPath);
        }
    }

}