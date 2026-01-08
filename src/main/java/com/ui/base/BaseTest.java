package com.ui.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ui.utilities.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * BaseTest is the parent class for all UI test classes.
 * 
 * Responsibilities:
 * - Load configuration properties (browser, URL, etc.)
 * - Initialize WebDriver before each test
 * - Launch browser and navigate to application URL
 * - Cleanly close browser after each test execution
 * 
 * This class promotes code reuse and consistent test setup across the framework.
 */
public class BaseTest {

    // WebDriver instance used by test classes
    protected WebDriver driver;

    // Properties object to store configuration values from config file
    protected Properties prop;

    /**
     * This method runs BEFORE every test method.
     * It initializes the browser and opens the application URL.
     */
    @BeforeMethod
    public void setup() {

        // Load configuration properties from config file
        prop = ConfigReader.loadProperties();

        // Read browser name from configuration
        String browserName = prop.getProperty("browser");

        // Launch browser based on config value
        if (browserName.equalsIgnoreCase("chrome")) {

            // Automatically downloads and sets up ChromeDriver binary
            WebDriverManager.chromedriver().setup();

            // Create Chrome browser instance
            driver = new ChromeDriver();
        }

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to application URL from config file
        driver.get(prop.getProperty("url"));
    }

    /**
     * This method runs AFTER every test method.
     * It closes the browser and releases system resources.
     */
    @AfterMethod
    public void tearDown() {

        // Close all browser windows and end WebDriver session
        if (driver != null) {
            driver.quit();
        }
    }
}
