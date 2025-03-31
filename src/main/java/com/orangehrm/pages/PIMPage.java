package com.orangehrm.pages;

import com.microsoft.playwright.Page;
import com.github.javafaker.Faker;
import com.orangehrm.utils.ExcelWriter;
import org.testng.Assert;

public class PIMPage {
    private final Page page;
    private final Faker faker;


    private final String btnAdd = "//button[contains(.,'Add')]";


    //AddEmployee page elements
    private final String tfFirstname = "//input[@placeholder='First Name']";
    private final String tfMiddlename = "//input[@placeholder='Middle Name']";
    private final String tfLastname = "//input[@placeholder='Last Name']";
    private final String tfEmployeeID = "//label[text()='Employee Id']/ancestor::div[contains(@class, 'oxd-input-group')]//input";
    private final String cbCreatelogin = "//span[contains(@class,'oxd-switch-input oxd-switch-input--active')]";
    private final String tfUsername = "//label[text()='Username']/ancestor::div[contains(@class, 'oxd-input-group')]//input";
    private final String tfPassword = "//label[text()='Password']/ancestor::div[contains(@class, 'oxd-input-group')]//input";
    private final String tfConfirmpassword = "//label[text()='Confirm Password']/ancestor::div[contains(@class, 'oxd-input-group')]//input";
    private final String btnCancel = "//button[contains(.,'Cancel')]";
    private final String btnSave = "//button[@type='submit']";


    public PIMPage(Page page) {
        this.page = page;
        this.faker = new Faker();
    }

    public void navigateToCreateEmployeeForm(){
        page.click(btnAdd);
    }

    public void createEmployee(){

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
        String password = "mit12345";

        page.fill(tfFirstname, firstName);
        page.fill(tfLastname, lastName);
        String employeeId = page.inputValue(tfEmployeeID);
        page.waitForSelector(cbCreatelogin);
        page.click(cbCreatelogin);
        page.fill(tfUsername, username);
        page.fill(tfPassword, password);
        page.fill(tfConfirmpassword, password);
        page.click(btnSave);

        String employeeName = "//h6[text()='" + firstName + " " + lastName + "']";
        page.waitForSelector(employeeName);
        String actualText = page.textContent(employeeName);

        String expectedText = firstName + " " + lastName;
        Assert.assertEquals(actualText, expectedText, "Employee name verification failed!");

        ExcelWriter.writeToExcel(firstName, lastName, username, employeeId, password);
        System.out.println(employeeId + " Employee has been created succesfully");
    }

}
