package com.ui.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
 * 
 * Enhancement:
 * - Runs normal Chrome locally
 * - Runs headless Chrome in CI/CD environments automatically
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

        if (browserName.equalsIgnoreCase("chrome")) {

            // Automatically download and setup ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Chrome options object to configure browser
            ChromeOptions options = new ChromeOptions();

            // Check if running in CI environment
            // GitHub Actions sets CI=true by default
            String ciEnv = System.getenv("CI");
            if (ciEnv != null && ciEnv.equalsIgnoreCase("true")) {
                // Headless mode for CI/CD
                options.addArguments("--headless=new"); // latest headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                System.out.println("Running tests in HEADLESS mode for CI.");
            } else {
                System.out.println("Running tests in LOCAL Chrome browser.");
            }

            // Initialize ChromeDriver with options
            driver = new ChromeDriver(options);
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
