package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
    public static String getData(String dataType) {
        String filePath = "src/test/config.properties";
        String result = null;

        try{
            InputStream input = new FileInputStream(filePath);
            Properties properties = new Properties();
            properties.load(input);
            result = properties.getProperty(dataType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
