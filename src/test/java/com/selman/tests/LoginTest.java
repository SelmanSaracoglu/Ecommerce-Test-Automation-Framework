package com.selman.tests;

import com.selman.pages.HomePage;
import com.selman.pages.SignupLoginPage;
import com.selman.utils.ConfigReader;
import com.selman.utils.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.ConfigurationGroupMethods;

public class LoginTest extends BaseTest {

    @Test(description = "Verify login with valid credentials using Config Data")
    public void testValidLogin() {

        // 1. Initialize Objects
        HomePage homePage = new HomePage(driver);

        // 2. Handle Cookies & Navigate
        homePage.acceptCookies();
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        // 3. Login using credentials from CONFIG.PROPERTIES
        loginPage.login(
                ConfigReader.getProperty("valid_email"),
                ConfigReader.getProperty("valid_password")
        );

        // 4. Assertion: Verify Login Success
        String loggedInText = loginPage.getLoggedInUserText();
        System.out.println("Login Status: " + loggedInText);
        Assert.assertTrue(loggedInText.contains("Logged in as"), "Login failed!");
    }

    @Test(description = "Verify Logout Functionality")
    public void testLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        loginPage.login(
                ConfigReader.getProperty("valid_email"),
                ConfigReader.getProperty("valid_password")
        );

        // --- TEST ACTIONS ---
        homePage.clickLogout();
        // --- ASSERTION ---
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        Assert.assertTrue(currentUrl.contains("/login"), "Logout failed! Not redirected to login page.");
        Assert.assertEquals(pageTitle, "Automation Exercise - Signup / Login", "Page title mismatch after logout!");
    }

    @Test(description = "Verify login with INVALID credentials")
    public void testInvalidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        // 2. Action: Login with RANDOM invalid data
        String randomEmail = DataUtils.getRandomEmail();
        String randomPassword = DataUtils.getRandomPassword();

        System.out.println("Testing Invalid Login With: " + randomEmail + " / " + randomPassword);

        loginPage.login(randomEmail, randomPassword);

        // 3. Assertion
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "Bug Found! User was redirected away from login page with invalid credentials!");
    }

    @Test(description = "Negative Test: Valid Email + Invalid Password")
    public void testLoginWithValidEmailInvalidPassword() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        // Strategy: Real Email (Config) + Fake Password (DataUtils)
        String validEmail = ConfigReader.getProperty("valid_email");
        String invalidPassword = DataUtils.getRandomPassword();

        System.out.println("Testing Valid Email + Invalid Password: " + validEmail + " / " + invalidPassword);
        loginPage.login(validEmail, invalidPassword);

        // Assert: URL should still be /login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "Security Breach! User logged in with WRONG password!");
    }

    @Test(description = "Negative Test: Invalid Email + Valid Password")
    public void testLoginWithInvalidEmailValidPassword() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        // Strategy: Fake Email (DataUtils) + Real Password (Config)
        String invalidEmail = DataUtils.getRandomEmail();
        String validPassword = ConfigReader.getProperty("valid_password");

        System.out.println("Testing Invalid Email + Valid Password: " + invalidEmail + " / " + validPassword);
        loginPage.login(invalidEmail, validPassword);

        // Assert: URL should still be /login
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Error! User logged in with non-existing email!");
    }

    @Test(description = "Negative Test: Invalid Email Format (Missing @ or domain)")
    public void testLoginWithInvalidEmailFormat() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        SignupLoginPage loginPage = homePage.clickSignupLogin();

        // Strategy: Malformed Email + Random Password
        String malformedEmail = "selman-invalid-format.com"; // Missing '@'
        String randomPassword = DataUtils.getRandomPassword();

        System.out.println("Testing Invalid Email Format: " + malformedEmail);
        loginPage.login(malformedEmail, randomPassword);

        // Assert:
        // 1. URL should definitely remain on /login
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Validation failed! Form accepted invalid email format.");


    }
}
