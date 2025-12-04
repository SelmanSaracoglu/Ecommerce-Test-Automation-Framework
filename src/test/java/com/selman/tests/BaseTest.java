package com.selman.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    /**
     * Setup method to initialize the WebDriver based on the browser parameter.
     * This method runs automatically before every @Test method.
     *
     * @param browser The browser name passed from testng.xml (default is "chrome").
     */

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {

        // Cross-Browser Logic: Switch based on the parameter
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*"); // Fix for Chrome connection issues
                options.addArguments("--start-maximized");
                // options.addArguments("--headless"); // Enable for CI/CD environments (GitHub Actions)
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;

            default:
                throw new IllegalArgumentException("Unsupported Browser: " + browser);
        }

        // Global configuration
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navigate to the target application
        driver.get("https://automationexercise.com");
    }

    /**
     * Teardown method to close the browser after test execution.
     * This method runs automatically after every @Test method.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Closes all browser windows and safely ends the session
        }
    }

}
