package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {

    // --- Locators ---
    // Başarılı mesajı: "ACCOUNT CREATED!"
    private final By accountCreatedHeader = By.cssSelector("h2[data-qa='account-created']");

    // Devam butonu
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    // --- Actions ---

    /**
     * Verifies if the 'Account Created' success message is visible.
     */
    public boolean isAccountCreatedMessageVisible() {
        return isDisplayed(accountCreatedHeader);
    }

    /**
     * Clicks Continue to go to the logged-in homepage.
     */
    public void clickContinue() {
        click(continueButton);
    }
}