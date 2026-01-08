package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.utilities.ConfigReader;
import com.ui.base.BaseTest;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;

/**
 * LoginTest validates login functionality using positive and negative scenarios.
 * 
 * Test Scenarios:
 * - Valid login using credentials from environment variables
 * - Invalid login using credentials from config.properties
 * 
 * This class extends BaseTest to reuse browser setup and teardown logic.
 */
public class LoginTest extends BaseTest {

    // Page object references
    LoginPage loginPage;
    HomePage homePage;

    /**
     * Valid login test.
     * 
     * Uses credentials from system environment variables:
     * - APP_USERNAME
     * - APP_PASSWORD
     * 
     * Validates successful login message.
     */
    @Test
    public void validLoginTest() {

        // Initialize Login Page
        loginPage = new LoginPage(driver);

        // Perform login using secure environment variables
        loginPage.login(
                ConfigReader.getEnv("APP_USERNAME"),
                ConfigReader.getEnv("APP_PASSWORD")
        );

        // Initialize Home Page after successful login
        homePage = new HomePage(driver);

        // Validate success message
        String message = homePage.getMessage();
        Assert.assertTrue(
                message.contains("You logged into a secure area"),
                "Expected successful login message not displayed"
        );
    }

    /**
     * Invalid login test.
     * 
     * Uses invalid credentials from config.properties file.
     * Validates error message displayed after failed login attempt.
     */
    @Test
    public void invalidLoginTest() {

        // Initialize Login Page
        loginPage = new LoginPage(driver);

        // Perform login using invalid credentials from config file
        loginPage.login(
                prop.getProperty("invalid.username"),
                prop.getProperty("invalid.password")
        );

        // Fetch failure message from login page
        String message = loginPage.getMessage();

        // Validate error message
        Assert.assertTrue(
                message.contains("Your username is invalid"),
                "Expected error message not displayed"
        );
    }
}
