package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // --- Locators (Using Stable 'data-qa' attributes as proof of "Stable Selectors") ---
    // Login Form Elements
    private final By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");

    // Header Elements (To verify successful login)
    private final By logoutLink = By.xpath("//a[contains(@href, '/logout')]");
    private final By loggedInAsText = By.xpath("//li[contains(., 'Logged in as')]");

    // Constructor matches the BasePage structure
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // --- Actions ---

    /**
     * Performs the login action with given credentials.
     * * @param email User email address
     * @param password User password
     */
    public void login(String email, String password) {
        // 'type' and 'click' methods come from BasePage with Explicit Waits
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
    }

    /**
     * Verifies if the Logout link is visible in the navbar.
     * This is a standard check for a successful login session.
     * * @return true if visible, false otherwise
     */
    public boolean isLogoutVisible() {
        return isDisplayed(logoutLink);
    }

    /**
     * Retrieves the 'Logged in as [Username]' text from the header.
     * Useful for verifying the correct user session.
     * * @return Full text string from the element
     */
    public String getLoggedInUserText() {
        return getText(loggedInAsText);
    }
}
