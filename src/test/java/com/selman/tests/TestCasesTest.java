package com.selman.tests;

import com.selman.pages.HomePage;
import com.selman.pages.TestCasesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCasesTest extends BaseTest {

    @Test(description = "Verify Test Cases Page Navigation")
    public void testVerifyTestCasePage() {

        // 1. Launch browser (Handled by BaseTest)
        // 2. Navigate to url (Handled by BaseTest)
        // 3. Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        Assert.assertTrue(homePage.isLogoVisible(), "Home Page is not visible!");

        // 4. Click on 'Test Cases' button
        TestCasesPage testCasesPage = homePage.clickTestCases();

        // 5. Verify user is navigated to test cases page successfully
        Assert.assertTrue(testCasesPage.isTestCasesPageLoaded(),
                "Failed to navigate to Test Cases page! Header not visible.");

        Assert.assertTrue(driver.getTitle().contains("Test Cases"),
                "Page Title is incorrect!");

        System.out.println("Test Case Passed: Navigation to Test Cases page successful.");
    }
}
