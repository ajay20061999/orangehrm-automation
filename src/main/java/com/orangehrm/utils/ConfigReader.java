package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void initProperties() {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties file", e);
        }
    }

    public static String get(String key) {
        if (properties == null) {
            initProperties();
        }
        return properties.getProperty(key);
    }
}
