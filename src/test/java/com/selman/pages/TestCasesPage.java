package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage {

    // --- LOCATORS ---
    private final By testCasesHeader = By.xpath("//h2[@class='title text-center']");

    public TestCasesPage (WebDriver driver) {
        super(driver);
    }

    // --- ACTIONS ---
    public boolean isTestCasesPageLoaded() {
        return isDisplayed(testCasesHeader);
    }
}
