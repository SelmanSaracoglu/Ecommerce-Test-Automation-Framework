package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // --- 1. HEADER LOCATORS ---
    private final By homeLogo = By.cssSelector("img[alt='Website for automation practice']");
    private final By homeLink = By.xpath("//a[contains(text(), 'Home')]");
    private final By productsLink = By.xpath("//a[contains(text(), 'Products')]");
    private final By cartLink = By.xpath("//a[contains(text(), 'Cart')]");
    private final By signupLoginLink = By.xpath("//a[contains(@href, '/login')]");
    private final By testCasesLink = By.xpath("//a[contains(text(), 'Test Cases')]");
    private final By apiTestingLink = By.xpath("//a[contains(text(), 'API Testing')]");
    private final By contactUsLink = By.xpath("//a[contains(text(), 'Contact us')]");

    // Cookie Consent
    private final By consentButton = By.cssSelector("button[aria-label='Consent']");

    // --- 2. BODY LOCATORS (Main Content) ---
    // Ana Slider (Carousel)
    private final By sliderCarousel = By.id("slider-carousel");
    // Sol taraftaki Kategori menüsü
    private final By categorySidebar = By.id("accordian");
    // Ortadaki "Features Items" (Öne Çıkan Ürünler) başlığı
    private final By featuresItemsHeader = By.cssSelector(".features_items h2.title");

    // --- SLIDER LOCATORS (Updated) ---

    // We explicitly locate Slide 1 and Slide 2 containers to check their 'class' attributes.
    // Using XPath index to ensure we grab the specific slide from the list.
    private final By slide1Container =
            By.xpath("(//div[@id='slider-carousel']//div[contains(@class,'item')])[1]");
    private final By slide2Container =
            By.xpath("(//div[@id='slider-carousel']//div[contains(@class,'item')])[2]");
    private final By slide3Container =
            By.xpath("(//div[@id='slider-carousel']//div[contains(@class,'item')])[3]");

    // Bottom indicators to switch slides manually
    // index 0 -> Slide 1, index 1 -> Slide 2, index 2 -> Slide 3
    private final By sliderIndicator1 =
            By.cssSelector("#slider-carousel .carousel-indicators li[data-slide-to='0']");
    private final By sliderIndicator2 =
            By.cssSelector("#slider-carousel .carousel-indicators li[data-slide-to='1']");
    private final By sliderIndicator3 =
            By.cssSelector("#slider-carousel .carousel-indicators li[data-slide-to='2']");

    // --- SLIDER ACTIONS ---
    /**
     * Checks if a specific slide is currently visible (active).
     * It checks if the HTML class contains "active".
     * * @param slideNumber The slide number (1, 2, or 3)
     * * @return true if the slide has class 'active', false otherwise.
     */
    public boolean isSlideActive(int slideNumber) {
        By targetSlide = null;

        if (slideNumber == 1) targetSlide = slide1Container;
        else if (slideNumber == 2) targetSlide = slide2Container;
        else if (slideNumber == 3) targetSlide = slide3Container;

        if (targetSlide != null) {
            // Get the 'class' attribute value (e.g., "item active")
            String classValue = driver.findElement(targetSlide).getAttribute("class");
            return classValue.contains("active");
        }
        return false;
    }

    /**
     * Clicks on the bottom indicators to switch slides manually
     * AND waits for the text content to actually change.
     * * @param slideNumber The slide number to switch to (1, 2, or 3).
     */
    public void switchSlide(int slideNumber) {
        By targetIndicator = null;
        By targetSlide = null;

        if (slideNumber == 1) {
            targetIndicator = sliderIndicator1;
            targetSlide = slide1Container;
        } else if (slideNumber == 2) {
            targetIndicator = sliderIndicator2;
            targetSlide = slide2Container;
        } else if (slideNumber == 3) {
            targetIndicator = sliderIndicator3;
            targetSlide = slide3Container;
        }

        if (targetIndicator != null) {
            click(targetIndicator);

            // ROBUST WAIT: Wait until the target slide actually gets the "active" class.
            // This confirms the animation finished and the slide is physically visible.
            waitForAttributeContains(targetSlide, "class", "active");
        }
    }


    // --- 3. FOOTER LOCATORS ---
    // "Subscription" alanı (Footer'ın yüklendiğinin en büyük kanıtıdır)
    private final By subscriptionHeader = By.xpath("//h2[contains(text(), 'Subscription')]");
    private final By subscriptionEmailInput = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    // En alttaki Copyright yazısı
    private final By footerBottomText = By.cssSelector(".footer-bottom p");


    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // --- Actions & Verifications ---

    public void acceptCookies() {
        try {
            if (isDisplayed(consentButton)) {
                click(consentButton);
            }
        } catch (Exception e) {
            // Ignore
        }
    }

    // --- Header Checks ---
    public boolean isLogoVisible() { return isDisplayed(homeLogo); }
    public boolean isHomeLinkVisible() { return isDisplayed(homeLink); }
    public boolean isProductsLinkVisible() { return isDisplayed(productsLink); }
    public boolean isCartLinkVisible() { return isDisplayed(cartLink); }
    public boolean isSignupLoginLinkVisible() { return isDisplayed(signupLoginLink); }
    public boolean isTestCasesLinkVisible() { return isDisplayed(testCasesLink); }
    public boolean isApiTestingLinkVisible() { return isDisplayed(apiTestingLink); }
    public boolean isContactUsLinkVisible() { return isDisplayed(contactUsLink); }

    // --- Body Checks ---
    public boolean isMainSliderVisible() {
        return isDisplayed(sliderCarousel);
    }

    public boolean isCategorySidebarVisible() {
        return isDisplayed(categorySidebar);
    }

    public boolean isFeaturesItemsSectionVisible() {
        return isDisplayed(featuresItemsHeader);
    }

    // --- Footer Checks ---
    public boolean isSubscriptionSectionVisible() {
        // Eğer Subscription başlığı ve Email kutusu varsa Footer gelmiş demektir.
        return isDisplayed(subscriptionHeader) && isDisplayed(subscriptionEmailInput);
    }

    public boolean isFooterCopyrightVisible() {
        return isDisplayed(footerBottomText);
    }

    // Navigation
    public SignupLoginPage clickSignupLogin() {
        click(signupLoginLink);
        return new SignupLoginPage(driver);
    }
}