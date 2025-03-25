package org.uma.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Properties properties;

    private ConfigurationManager() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace(); // Consider more robust error handling
        }
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getApiKey() {
        return properties.getProperty("api.key");
    }

    public String getAccessToken() {
        return properties.getProperty("access.token");
    }

 
}