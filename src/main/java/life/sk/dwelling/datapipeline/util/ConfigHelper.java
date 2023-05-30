package life.sk.dwelling.datapipeline.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigHelper {

  public static Properties readProperties(String fileName) {
    Properties prop = new Properties();
    URL propUrl = ConfigHelper.class.getClassLoader().getResource(fileName);
    if (propUrl == null) {
      throw new RuntimeException("Properties file not found.");
    }

    try (InputStream propStream = new FileInputStream(propUrl.getPath())) {
      prop.load(propStream);
    } catch (IOException | NullPointerException e) {
      throw new RuntimeException(e.getMessage());
    }

    return prop;
  }
}
