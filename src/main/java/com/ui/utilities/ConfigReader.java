package com.ui.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * ConfigReader is a utility class responsible for loading
 * configuration values required by the automation framework.
 * 
 * It supports:
 * - Reading properties from config.properties file
 * - Reading sensitive values from system environment variables
 * 
 * This approach improves security and configurability across environments.
 */
public class ConfigReader {

    // Stores loaded configuration properties
    private static Properties prop;

    /**
     * Loads properties from the config.properties file.
     *
     * @return Properties object containing all config values
     */
    public static Properties loadProperties() {
        try {
            // Open input stream to config file
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");

            // Create Properties object
            prop = new Properties();

            // Load properties into memory
            prop.load(fis);

        } catch (Exception e) {
            // Print stack trace if file is not found or loading fails
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * Reads value from system environment variables.
     * Useful for sensitive information like passwords, tokens, secrets.
     *
     * Example:
     * export USER_PASSWORD=secret123
     *
     * @param key Environment variable name
     * @return Value of the environment variable
     */
    public static String getEnv(String key) {

        // Fetch value from system environment variables
        String value = System.getenv(key);

        // Fail execution if environment variable is missing
        if (value == null) {
            throw new RuntimeException(
                "Environment variable not set: " + key
            );
        }

        return value;
    }
}
