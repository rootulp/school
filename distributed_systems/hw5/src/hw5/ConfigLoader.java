package hw5;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
  static File configFile = new File("config.properties");
  static Properties props = loadProperties();
  
  public static Properties loadProperties() {
    Properties props = new Properties();
    try {
      FileReader reader = new FileReader(configFile);
      props.load(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return props;
  }
}
