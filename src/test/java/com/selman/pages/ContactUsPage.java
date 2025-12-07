package com.selman.pages;

import com.selman.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {

    // --- LOCATORS ---
    private final By getInTouchHeader = By.xpath("//h2[contains(text(), 'Get In Touch')]");

    // Form Fields
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By subjectInput = By.name("subject");
    private final By messageInput = By.id("message");

    // File Upload Input (type='file')
    private final By uploadFileInput = By.name("upload_file");
    private final By submitButton = By.name("submit");

    // Success Message
    private final By successMessage = By.cssSelector(".status.alert-success");
    private final By homeButton = By.xpath("//span[contains(text(), ' Home')]"); // Button text may vary slightly

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    // --- ACTIONS ---

    public boolean isGetInTouchVisible() {
        return isDisplayed(getInTouchHeader);
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageInput, message);
    }

    /**
     * Uploads a file using sendKeys method.
     * @param absoluteFilePath Full path to the file on your local machine.
     */
    public void uploadFile(String absoluteFilePath) {
        // Selenium'da dosya yüklemek için 'Upload' butonuna tıklanmaz!
        // type='file' olan input elementine dosya yolu gönderilir.
        driver.findElement(uploadFileInput).sendKeys(absoluteFilePath);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    /**
     * Handles the browser alert (OK/Cancel popup).
     */
    public void acceptAlert() {
        // Wait for alert to be present (Explicit Wait gerekebilir ama genelde hızlı çıkar)
        try {
            driver.switchTo().alert().accept(); // "OK" butonuna basar
        } catch (Exception e) {
            System.out.println("No alert appeared.");
        }
    }

    public boolean isSuccessMessageVisible() {
        return isDisplayed(successMessage);
    }

    public void clickHome() {
        click(homeButton);
    }
}