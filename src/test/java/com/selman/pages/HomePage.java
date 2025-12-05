package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // --- Locators ---

    // Navbar Elements

    private final By signupLoginLink = By.xpath("//a[contains(@href, '/login')]");
    private final By homeLink = By.xpath("//a[contains(text(), 'Home')]");
    private final By productsLink = By.xpath("//a[contains(@href, '/products')]");

    // Main Content
    // This creates a stable check to ensure we are actually on the Home Page
    private final By sliderCarousel = By.id("slider-carousel");

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // --- Actions ---

    /**
     * Navigates to the Login/Signup page by clicking the navbar link.
     * @return LoginPage object (Fluent Interface Pattern)
     */

    public boolean isHomePageVisible() {
        return isDisplayed(sliderCarousel);
    }

    /**
     * Clicks on the 'Products' link in the navbar.
     */
    public void clickProducts() {
        click(productsLink);
    }
}
