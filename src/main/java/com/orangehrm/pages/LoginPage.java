package com.orangehrm.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import static org.testng.AssertJUnit.assertEquals;

public class LoginPage {
    private final Page page;

    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLoginPage(String url) {
        page.navigate(url);
        assertEquals("OrangeHRM", page.title());
    }

    public void login(String username, String password) {
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

    public void logoutVerification(){
        page.waitForSelector(loginButton,new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }
}
