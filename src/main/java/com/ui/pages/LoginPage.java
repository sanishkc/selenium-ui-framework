package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage represents the Login screen of the application.
 * 
 * This Page Object:
 * - Stores all element locators related to the login page
 * - Provides reusable methods to perform login actions
 * - Encapsulates UI interaction logic (POM best practice)
 */
public class LoginPage {

    // WebDriver instance used to interact with the browser
    private WebDriver driver;

    // =========================
    // Locators
    // =========================

    // Username input field
    private By username = By.id("username");

    // Password input field
    private By password = By.id("password");

    // Login button
    private By loginButton = By.cssSelector("button[type='submit']");

    // Message banner (used for both success and failure messages)
    private By messageBanner = By.xpath("//*[@id=\"flash\"]");

    /**
     * Constructor initializes the WebDriver for this page.
     *
     * @param driver WebDriver instance passed from test class
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // =========================
    // Page Actions
    // =========================

    /**
     * Enters username into the username input field.
     *
     * @param user Username value
     */
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    /**
     * Enters password into the password input field.
     *
     * @param pass Password value
     */
    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    /**
     * Clicks the Login button.
     */
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    /**
     * Returns the text message displayed after login attempt.
     * This can be either success or failure message.
     *
     * @return Message text displayed on screen
     */
    public String getMessage() {
        return driver.findElement(messageBanner).getText();
    }

    /**
     * Performs a complete login action using provided credentials.
     * This method improves readability in test scripts.
     *
     * @param user Username
     * @param pass Password
     */
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}