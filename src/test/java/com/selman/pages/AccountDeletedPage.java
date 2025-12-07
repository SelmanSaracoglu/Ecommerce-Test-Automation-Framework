package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {

    private final By accountDeletedHeader = By.cssSelector("h2[data-qa='account-deleted']");

    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    // --- Actions ---

    /*  Verifies if the 'Account Deleted' success message is visible.
        Uses Explicit Wait, so it handles timing issues perfectly.  */
    public boolean isAccountDeletedMessageVisible() { return isDisplayed(accountDeletedHeader); }

    /* Clicks Continue to finish the cycle. */
    public void clickContinue() { click(continueButton); }

}
