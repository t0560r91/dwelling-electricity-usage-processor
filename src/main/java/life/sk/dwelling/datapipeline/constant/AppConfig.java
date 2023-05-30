package life.sk.dwelling.datapipeline.constant;

import life.sk.dwelling.datapipeline.util.ConfigHelper;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

// Note: Using constants vs config for app configuration.
public class AppConfig {
    // TODO: Turn some hard coded values into constants where appropriate.
    public static final String ACCOUNT_ID_COLNAME;
    public static final String SERVICE_ADDRESS_COLNAME;
    public static final String DAY_COLNAME;
    public static final String CONSUMPTION_COLNAME;
    public static final String TEMPERATURE_COLNAME;
    public static final DateTimeFormatter TARGET_DATE_FORMATTER;

    static {
        Properties prop = ConfigHelper.readProperties("application.properties");

        // TODO: Revert colnames from the source data and actually use these mapping.
        ACCOUNT_ID_COLNAME = prop.getProperty("account_id_colname");
        SERVICE_ADDRESS_COLNAME = prop.getProperty("service_address_colname");
        DAY_COLNAME = prop.getProperty("day_colname");
        CONSUMPTION_COLNAME = prop.getProperty("consumption_colname");
        TEMPERATURE_COLNAME = prop.getProperty("temperature_colname");

        TARGET_DATE_FORMATTER = DateTimeFormatter.ofPattern(prop.getProperty("target_date_format"));
    }
}
