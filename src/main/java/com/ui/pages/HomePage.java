package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HomePage represents the landing page displayed after a successful login.
 * 
 * This Page Object:
 * - Stores UI element locators related to Home Page
 * - Provides methods (actions) to interact with those elements
 * - Keeps UI logic separate from test logic (POM best practice)
 */
public class HomePage {

    // WebDriver instance used to interact with the browser
    private WebDriver driver;

    /**
     * Locator for the success message shown after successful login.
     * Example message: "You logged into a secure area!"
     */
    private By successMessage = By.xpath("//*[@id=\"flash\"]");

    /**
     * Constructor initializes the WebDriver for this page.
     *
     * @param driver WebDriver instance passed from test or previous page
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Fetches the success message text displayed on the Home page.
     *
     * @return String value of success message
     */
    public String getMessage() {
        return driver.findElement(successMessage).getText();
    }
}
