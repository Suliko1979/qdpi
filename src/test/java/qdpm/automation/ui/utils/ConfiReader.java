package qdpm.automation.ui.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class ConfiReader {
    private static Properties properties;

    static {
        String filePath = "/Users/user1/IdeaProjects/qdpm/src/test/resources/properties/qdpm.properties";


        try (FileInputStream input = new FileInputStream(filePath)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println("File not found or error reading properties");
            e.printStackTrace();
        }
    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }
}