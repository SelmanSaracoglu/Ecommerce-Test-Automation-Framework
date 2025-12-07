package com.selman.tests;

import com.selman.pages.*;
import com.selman.utils.ConfigReader;
import com.selman.utils.DataUtils;
import org.openqa.selenium.devtools.v85.runtime.Runtime;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest{

    @Test(description = "Full User Journey: Register -> Logout -> Login -> Delete Account")
    public void testUserLifecycle() {
        // --- 1. DATA GENERATION (Store in variables)
        String username = DataUtils.getRandomFirstName();
        String email = DataUtils.getRandomEmail();
        String password = DataUtils.getRandomPassword();

        System.out.println("Starting Lifecycle Test for: " + username + " | " + email + " | " + password);

        // --- 2. REGISTRATION FLOW ---
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();

        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        SignupPage signupPage = signupLoginPage.signup(username, email);
        Assert.assertTrue(signupPage.isPageLoaded(), "Signup Form did not load!");

        // Fill Account Info
        signupPage.fillAccountDetails(password, "12", "January", "1990");
        // Fill Address Info
        signupPage.fillAddressDetails(
            username,
            DataUtils.getRandomLastName(),
            "Selman Corp",
            DataUtils.getRandomAddress(),
            DataUtils.getRandomState(),
            DataUtils.getRandomCity(),
            DataUtils.getRandomZipCode(),
            DataUtils.getRandomMobileNumber()
        );

        // Click Create Account
        signupPage.clickCreateAccount();

        // 5. AccountCreatedPage Validation
        AccountCreatedPage createdPage = new AccountCreatedPage(driver);
        Assert.assertTrue(createdPage.isAccountCreatedMessageVisible(),
                "Test Failed! 'Account Created' message not visible.");

        createdPage.clickContinue();

        // --- 3. VERIFY LOGGED IN STATE ---
        // Now we are on HomePage but logged in.
        // Reuse loginPage methods or create a specific LoggedInPage.
        // For simplicity, let's verify via LoginPage locator logic (reused).
        SignupLoginPage dashboardCheck = new SignupLoginPage(driver);
        String loggedInText = dashboardCheck.getLoggedInUserText();
        Assert.assertTrue(loggedInText.contains(username), "Logged in username does not match!");

        // --- 4. LOGOUT ---
        System.out.println("Logging out...");
        // Logout link is in the header, we can click it via a locator.
        // Ideally, header links should be in a common component, but let's grab it via URL for now.
        driver.get("https://automationexercise.com/logout");

        // --- 5. LOGIN AGAIN (Using stored variables) ---
        System.out.println("Logging in again with generated credentials...");

        // Ensure we are on login page
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Not redirected to login page!");

        // Use the SAME email and password we generated at step 1
        signupLoginPage.login(email, password);

        // --- 6. VERIFY LOGIN SUCCESS ---
        String newLoggedInText = dashboardCheck.getLoggedInUserText();
        Assert.assertTrue(newLoggedInText.contains(username), "Re-login failed!");

        // --- 7. DELETE ACCOUNT (Clean Up) ---
        // This is a "Pro Move". Always clean up the data you created.
        System.out.println("Deleting account to clean up...");
        driver.get("https://automationexercise.com/delete_account");

        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);

        Assert.assertTrue(deletedPage.isAccountDeletedMessageVisible(),
                "Account deletion failed! Message not displayed.");

        deletedPage.clickContinue();

        System.out.println("Test Cycle Completed Successfully: Register -> Login -> Delete");
    }

    @Test(description = "Test Case 5: Register User with existing email")
    public void testRegisterWithExistingEmail() {

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isLogoVisible(),"Home Page is not visible!");

        // 4. Click on 'Signup / Login' button
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        // 5. Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.isNewUserSignupHeaderVisible(), "'New User Signup!' header is not visible!");

        // 6. Enter name and ALREADY REGISTERED email address
        // Strategy: We use the 'valid_email' from config.properties
        String name = "Existing User";
        String existingEmail = ConfigReader.getProperty("valid_email");

        System.out.println("Testing Registration with Existing Email: " + existingEmail);

        // 7. Click 'Signup' button
        loginPage.signup(name, existingEmail);

        // 8. Verify error 'Email Address already exist!' is visible
        Assert.assertTrue(loginPage.isEmailAlreadyExistsErrorVisible(),
                "Error message 'Email Address already exist!' is NOT visible!");





    }
}
