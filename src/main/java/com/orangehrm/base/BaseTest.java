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
        test = extent.createTest(method.getName());  // Create a test with the method name
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) throws IOException {
        // Log the status of the test (Pass/Fail/Skip)
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            // Capture screenshot on failure
            captureScreenshot(result);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }

        // Ensure that the page is closed after each test
        if (page != null) page.close();
    }

    @AfterClass
    public void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @AfterSuite
    public void tearDownReport() {
        // Flush the ExtentReports instance to write the results to the HTML file
        extent.flush();
    }

    private void captureScreenshot(ITestResult result) throws IOException {
        // Capture screenshot only if the test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            // Generate the screenshot path (relative to the report folder)
            String screenshotFolder = "test-output/screenshots/";
            String screenshotPath = screenshotFolder + result.getName() + "-" + System.currentTimeMillis() + ".png";

            // Ensure the screenshots folder exists, create if not
            java.nio.file.Files.createDirectories(Paths.get(screenshotFolder));

            // Take a screenshot and save it in the "screenshots" folder
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

            // Fix for relative path issue: Ensure the path is relative to the HTML report location
            String relativeScreenshotPath = screenshotPath.replace("test-output/", "");

            // Attach the screenshot to the ExtentReports log (relative path)
            test.addScreenCaptureFromPath(relativeScreenshotPath);
        }
    }
}