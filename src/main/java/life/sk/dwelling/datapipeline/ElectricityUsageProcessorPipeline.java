package life.sk.dwelling.datapipeline;

import life.sk.dwelling.datapipeline.component.connector.ElectricityUsageConnector;
import life.sk.dwelling.datapipeline.component.connector.RawElectricityUsageConnector;
import life.sk.dwelling.datapipeline.component.entity.ElectricityUsage;
import life.sk.dwelling.datapipeline.constant.AppConfig;
import life.sk.dwelling.datapipeline.subcomponent.reader.DataReader;
import life.sk.dwelling.datapipeline.component.entity.DataEntity;
import life.sk.dwelling.datapipeline.component.entity.RawElectricityUsage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElectricityUsageProcessorPipeline extends DataPipeline {
  // TODO: Auto inject dependencies.
  private final RawElectricityUsageConnector rawElectricityUsageConnector;
  public ElectricityUsageProcessorPipeline(
      RawElectricityUsageConnector rawElectricityUsageConnector,
      ElectricityUsageConnector electricityUsageConnector) {

    // Manually injecting dependencies.
    this.rawElectricityUsageConnector = rawElectricityUsageConnector;
    this.dstDataConnector = electricityUsageConnector;
  }

  // TODO: Handle data extraction with retry?
  @Override
  protected Map<Class<? extends DataEntity>, List<? extends DataEntity>> extractData()
      throws IOException {

    List<RawElectricityUsage> incomingRawElectricityUsage;

    // Read the source dataset.
    // TODO: It should be okay to pass in non-slash-trailing file path in config.
    URL dataFileLocation = new URL(
        this.rawElectricityUsageConnector.getLocation(),
        this.targetDate.getYear()
            + "/" + this.targetDate.format(AppConfig.TARGET_DATE_FORMATTER)
            + "." + this.rawElectricityUsageConnector.getDataReader().getDataFormat().getExtension());

    DataReader reader = this.rawElectricityUsageConnector.getDataReader();
    incomingRawElectricityUsage = reader.readData(dataFileLocation.openStream(), RawElectricityUsage.class);

    // Pack the dataset.
    this.incomingDataPack.put(RawElectricityUsage.class, incomingRawElectricityUsage);

    return this.incomingDataPack;
  }

  @Override
  protected List<ElectricityUsage> transformData(
      Map<Class<? extends DataEntity>, List<? extends DataEntity>> incomingData) {

    // TODO: Alternate approach: taking data as tuple?
    List<ElectricityUsage> transformedData = new ArrayList<>();
    RawElectricityUsage record;

    for (DataEntity rawRecord : incomingData.get(RawElectricityUsage.class)) {
      record = (RawElectricityUsage) rawRecord;
      ElectricityUsage transformedRecord = new ElectricityUsage(
          record.getAccountId().replaceAll("'", ""),
          record.getServiceAddress(),
          Float.parseFloat(record.getConsumptionKwh()),
          Integer.parseInt(record.getTemperatureF()),
          Date.valueOf(LocalDate.of(
              this.targetDate.getYear(),
              this.targetDate.getMonthValue(),
              Integer.parseInt(record.getDay().split("-")[0]))),
          Timestamp.from(this.executionTimestamp.toInstant()),
          Timestamp.from(this.executionTimestamp.toInstant()));

      System.out.println(transformedRecord);

      transformedData.add(transformedRecord);
    }

    return transformedData;
  }

}
