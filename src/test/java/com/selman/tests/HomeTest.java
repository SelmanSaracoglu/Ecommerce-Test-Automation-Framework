package com.selman.tests;

import com.selman.tests.BaseTest;
import com.selman.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test(description = "Smoke Test: Verify Homepage (Header, Body, Footer)")
    public void testHomepageElements() {
        // 1. Initialize Page
        HomePage homePage = new HomePage(driver);

        // 2. Handle Cookies
        homePage.acceptCookies();

        // 3. Verify Page Title
        Assert.assertEquals(driver.getTitle(), "Automation Exercise", "Page title is incorrect!");

        // --- STEP 1: Verify HEADER Section ---
        System.out.println("Checking Header Elements...");
        Assert.assertTrue(homePage.isLogoVisible(), "Logo is missing!");
        Assert.assertTrue(homePage.isHomeLinkVisible(), "Home link missing!");
        Assert.assertTrue(homePage.isProductsLinkVisible(), "Products link missing!");
        Assert.assertTrue(homePage.isCartLinkVisible(), "Cart link missing!");
        Assert.assertTrue(homePage.isSignupLoginLinkVisible(), "Signup/Login link missing!");
        Assert.assertTrue(homePage.isTestCasesLinkVisible(), "Test Cases link missing!");
        Assert.assertTrue(homePage.isApiTestingLinkVisible(), "API Testing link missing!");
        Assert.assertTrue(homePage.isContactUsLinkVisible(), "Contact Us link missing!");

        // --- STEP 2: Verify BODY Section ---
        System.out.println("Checking Body Elements...");
        Assert.assertTrue(homePage.isMainSliderVisible(), "Main Slider/Carousel is not displayed!");
        Assert.assertTrue(homePage.isCategorySidebarVisible(), "Category Sidebar (Left) is missing!");
        Assert.assertTrue(homePage.isFeaturesItemsSectionVisible(), "'Features Items' product grid is missing!");

        // --- STEP 3: Verify FOOTER Section ---
        // Scroll down is usually handled automatically by Selenium when checking isDisplayed,
        // but explicit wait handles visibility.
        System.out.println("Checking Footer Elements...");
        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Footer Subscription section is missing!");
        Assert.assertTrue(homePage.isFooterCopyrightVisible(), "Footer Copyright text is missing!");

        System.out.println("Full Page Smoke Test Passed!");
    }

    @Test(description = "Verify Main Slider Functionality (Switching Slides via Class Attribute)")
    public void testSliderFunctionality() {
        // 1. Initialize Page
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();

        // 2. Verify Initial State (Slide 1 should be active)
        Assert.assertTrue(homePage.isSlideActive(1), "Slide 1 should be active by default!");
        Assert.assertFalse(homePage.isSlideActive(2), "Slide 2 should NOT be active initially!");

        // 3. Switch to Slide 2
        System.out.println("Switching to Slide 2...");
        homePage.switchSlide(2);

        // 4. Verify New State (Slide 2 should be active now)
        Assert.assertTrue(homePage.isSlideActive(2), "Slide 2 did not become active!");
        Assert.assertFalse(homePage.isSlideActive(1), "Slide 1 should no longer be active!");

        System.out.println("Slider Test Passed: DOM Class attributes updated correctly.");
    }
}