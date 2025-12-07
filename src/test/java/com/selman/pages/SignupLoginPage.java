package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage extends BasePage {

    // --- Login Locators ---
    private final By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");

    // --- SIGNUP LOCATORS (Burası kritik!) ---
    private final By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");

    private final By loggedInAsText = By.xpath("//a[contains(., 'Logged in as')]");

    // Constructor
    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    // --- Actions ---

    /**
     * Performs login with existing credentials.
     * @param email Registered email
     * @param password Registered password
     */
    public void login(String email, String password) {
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
    }

    /**
     * Fills the "New User Signup!" section and clicks the Signup button.
     * This action redirects the user to the SignupPage (Form).
     *
     * @param name  New user's name
     * @param email New user's email (Must be unique)
     * @return SignupPage object (Fluent Interface)
     */
    public SignupPage signup(String name, String email) {
        type(signupNameInput, name);
        type(signupEmailInput, email);
        click(signupButton);
        return new SignupPage(driver); // Sayfa geçişi olduğu için yeni objeyi dönüyoruz
    }

    /**
     * Retrieves the 'Logged in as [Username]' text from the header.
     * Essential for verifying that the user session is active.
     * @return Full text string (e.g., "Logged in as John")
     */
    public String getLoggedInUserText() {
        return getText(loggedInAsText);
    }
}
