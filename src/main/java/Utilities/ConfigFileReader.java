package Utilities;

import TestSetup.TestSetup;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigFileReader extends TestSetup {

    private static Properties properties;
    private final String propertyFilePath = "//src//main//resources//Configuration.properties";
    public static String url;

    public ConfigFileReader() throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getBaseUrl() {
        url = properties.getProperty("BASE_URL");
        return  url;
    }
}
