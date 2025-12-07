package com.selman.tests;

import com.selman.pages.ContactUsPage;
import com.selman.pages.HomePage;
import com.selman.utils.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ContactUsTest extends BaseTest{

    @Test(description = "Contact Us Form with File Upload")
    public void testContactUsForm() {
        // 1. Init & Navigate
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();

        // 2. Verify Home & Click Contact Us
        Assert.assertTrue(homePage.isLogoVisible(), "Home page not visible");
        ContactUsPage contactPage = homePage.clickContactUs();

        // 3. Verify 'Get In Touch'
        Assert.assertTrue(contactPage.isGetInTouchVisible(), "'Get In Touch' header not visible");

        // 4. Fill Form
        contactPage.fillContactForm(
                DataUtils.getRandomFirstName(),
                DataUtils.getRandomEmail(),
                "Bug Report - File Upload Test",
                "This is a test message regarding file upload functionality."
        );

        // 5. Upload File
        // src/test/resources
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/src/test/resources/test_file.txt";

        // Windows/Mac
        filePath = filePath.replace("/", File.separator);

        System.out.println("Uploading file from: " + filePath);
        contactPage.uploadFile(filePath);

        // 6. Submit & Handle Alert
        contactPage.clickSubmit();
        contactPage.acceptAlert();

        // 7. Verify Success
        Assert.assertTrue(contactPage.isSuccessMessageVisible(),
                "Success message not visible after submitting form!");

        // 8. Go Home
        contactPage.clickHome();
        Assert.assertTrue(homePage.isLogoVisible(), "Failed to return to Home page");
    }

}
