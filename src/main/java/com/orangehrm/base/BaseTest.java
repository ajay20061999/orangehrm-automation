package com.orangehrm.base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;

    @BeforeClass
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeMethod
    public void createPage() {
        page = browser.newPage();
    }

    @AfterMethod
    public void closePage() {
        if (page != null) page.close();
    }

    @AfterClass
    public void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
