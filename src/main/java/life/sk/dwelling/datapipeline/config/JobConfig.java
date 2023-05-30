package life.sk.dwelling.datapipeline.config;

import life.sk.dwelling.datapipeline.util.ConfigHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class JobConfig extends CommonJobConfig {
  private final URL rawElectricityUsageDataLocation;
  private final URL electricityUsageDataLocation;

  public JobConfig(
      LocalDate startDate, LocalDate endDate, URL rawElectricityUsageDataLocation, URL electricityUsageDataLocation) {

    super(startDate, endDate);
    this.rawElectricityUsageDataLocation = rawElectricityUsageDataLocation;
    this.electricityUsageDataLocation = electricityUsageDataLocation;
  }

  public URL getRawElectricityUsageDataLocation() {
    return rawElectricityUsageDataLocation;
  }

  public URL getElectricityUsageDataLocation() {
    return electricityUsageDataLocation;
  }

  public static JobConfig getJobConfig() {
    // TODO: Turn some hard coded values into constants where appropriate.
    JobConfig config;
    Properties prop = ConfigHelper.readProperties("application.properties");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    try {
      // TODO: Validate input value and format.
      config = new JobConfig(
          LocalDate.parse(prop.getProperty("start_date"), dateFormatter),
          LocalDate.parse(prop.getProperty("end_date"), dateFormatter),
          new URL(prop.getProperty("raw_electricity_usage_data_location")),
          new URL(prop.getProperty("electricity_usage_data_location")));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }

    return config;
  }
}
