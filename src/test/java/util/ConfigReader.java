package util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * config file to read static properties
 */
public class ConfigReader {
    static Logger log = Logger.getLogger(ConfigReader.class.getName());

    private final String filePath = "src/test/resources/properties/ConfigProperty.properties";
    public final Properties properties;

    public ConfigReader(){
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(filePath));
            properties = new Properties();
            properties.load(br);
            br.close();
        } catch (FileNotFoundException e) {
            log.error("Configuration File not found at location: "+filePath);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("RuntimeException found: "+e);
            throw new RuntimeException(e);
        }
    }
}
