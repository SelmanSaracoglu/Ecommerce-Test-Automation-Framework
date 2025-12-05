package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    // --- LOCATORS ---

    // Header to verify we are on the correct page ("Enter Account Information")
    private final By pageHeader = By.xpath("//b[contains(text(), 'Enter Account Information')]");

    // Title (Radio Buttons)
    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");

    // Account Details
    private final By passwordInput = By.id("password");
    private final By daysDropdown = By.id("days");
    private final By monthsDropdown = By.id("months");
    private final By yearsDropdown = By.id("years");

    // Checkboxes
    private final By newsletterCheckbox = By.id("newsletter");
    private final By optinCheckbox = By.id("optin");

    // Address Information
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyInput = By.id("company");
    private final By address1Input = By.id("address1");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");

    // Submit Button
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    // --- CONSTRUCTOR ---
    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // --- ACTIONS ---

    public boolean isPageLoaded() {
        return isDisplayed(pageHeader);
    }

    /**
     * Fills the Account Information section.
     */
    public void fillAccountDetails(String password, String day, String month, String year) {
        selectCheckbox(titleMr); // Defaulting to Mr. for this test
        type(passwordInput, password);
        selectByVisibleText(daysDropdown, day);
        selectByVisibleText(monthsDropdown, month);
        selectByVisibleText(yearsDropdown, year);
        selectCheckbox(newsletterCheckbox);
        selectCheckbox(optinCheckbox);
    }

    /**
     * Fills the Address Information section.
     */
    public void fillAddressDetails(String fName, String lName, String company, String address,
                                   String state, String city, String zip, String mobile) {
        type(firstNameInput, fName);
        type(lastNameInput, lName);
        type(companyInput, company);
        type(address1Input, address);
        selectByVisibleText(countryDropdown, "Canada"); // Selecting Canada as example
        type(stateInput, state);
        type(cityInput, city);
        type(zipcodeInput, zip);
        type(mobileNumberInput, mobile);
    }

    /**
     * Clicks the 'Create Account' button.
     * Note: In a real framework, this might return the next Page Object (AccountCreatedPage).
     */
    public void clickCreateAccount() {
        click(createAccountButton);
    }
}