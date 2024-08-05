package com.angelalign.userstorage.config;


import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Component
public class CustomConfigLoader {

    private Properties properties;

    public CustomConfigLoader() {
        this.properties = new Properties();
        loadYamlProperties("application.yaml");
    }

    private void loadYamlProperties(String yamlFilePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(yamlFilePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("YAML file not found: " + yamlFilePath);
            }
            Yaml yaml = new Yaml();
            Map<String, Object> yamlMap = yaml.load(inputStream);
            for (Map.Entry<String, Object> entry : yamlMap.entrySet()) {
                properties.put(entry.getKey(), entry.getValue().toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML properties", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
