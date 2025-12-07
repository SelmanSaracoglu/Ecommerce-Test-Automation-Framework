package com.selman.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor for BasePage.
     * Initializes the WebDriver and sets up Explicit Wait.
     * @param driver The WebDriver instance from the Test class.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Global Explicit Wait: Waits up to 10 seconds for elements to be interactable
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Waits for the element to be clickable and performs a click action.
     * This ensures the element is ready before interacting, reducing flakiness.
     * @param locator The By locator of the element to click.
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Waits for the element to be visible, clears any existing text,
     * and sends the new keys.
     * @param locator The By locator of the input field.
     * @param text    The text to be entered.
     */
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for the element to be visible and retrieves its text.
     * @param locator The By locator of the element.
     * @return The visible text of the element.
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Checks if an element is displayed on the page.
     * Handles exceptions if the element is not found within the wait time.
     * @param locator The By locator of the element.
     * @return true if the element is displayed, false otherwise.
     */
    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Selects an option from a dropdown by visible text.
     * @param locator The locator of the <select> element
     * @param text The visible text to select
     */
    protected void selectByVisibleText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    /* Selects a radio button or checkbox if it is not already selected. */
    protected void selectCheckbox(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!element.isSelected()) {
            element.click();
        }
    }

    /*  Waits until an element has a specific attribute value.
        Example: Wait until 'class' attribute contains 'active'.    */

    protected void waitForAttributeContains(By locator, String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
}
