package org.selenium.pom.utils;

import org.selenium.pom.constans.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE -> properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
            case PRODUCTION -> properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            default -> throw new IllegalStateException("Invalid env type: " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the stg_config.properties file");

    }

    public String getUserName() {
        String prop = properties.getProperty("username");
        if (prop != null) return prop;
        else throw new RuntimeException("property userName is not specified in the stg_config.properties file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the stg_config.properties file");

    }
    public String getEmail() {
        String prop = properties.getProperty("email");
        if (prop != null) return prop;
        else throw new RuntimeException("property email is not specified in the stg_config.properties file");

    }

}