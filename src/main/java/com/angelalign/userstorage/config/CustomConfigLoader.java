package com.angelalign.userstorage.config;


import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class CustomConfigLoader {

    private static final Properties properties = new Properties();


    static {

        try {

            String absolutePath = "D:\\keycloak\\keycloak-25.0.2\\providers\\application.properties";
            FileInputStream inputStream = new FileInputStream(absolutePath);
            properties.load(inputStream);

        } catch (Exception e) {
            System.err.println("Configuration file 'application' not found: " + e.getMessage());
        }
    }


    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    // 如果需要获取整个Properties对象
    public static Properties getProperties() {
        return properties;
    }


    public static void main(String[] args) {

       String datasource = getPropertyValue("datasource");
       String username = getPropertyValue("username");
       String password = getPropertyValue("password");

       System.out.println("get datasource" + datasource);
       System.out.println("get username" + username);
       System.out.println("get password" + password);

    }

}


