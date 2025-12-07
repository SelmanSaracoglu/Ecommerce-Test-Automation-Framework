package com.selman.utils;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class ConfigReader {

    private static Properties properties;

    static {
        try{
            String path = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new java.util.Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Config.properties file not found or could not be loaded!");
        }
    }

    /**
     * Gets the value of the specified key from config.properties
     * @param key The key to look for (e.g., "url", "browser")
     * @return The value associated with the key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
