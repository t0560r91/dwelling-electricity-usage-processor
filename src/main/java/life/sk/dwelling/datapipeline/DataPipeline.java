/*
  V1: Batch ETL support for file-like data.
 */
package life.sk.dwelling.datapipeline;

import life.sk.dwelling.datapipeline.component.connector.DataConnector;
import life.sk.dwelling.datapipeline.component.entity.DataEntity;
import life.sk.dwelling.datapipeline.constant.AppConfig;
import life.sk.dwelling.datapipeline.subcomponent.writer.DataWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
  DataPipeline framework that
    provide interface for general ETL tasks
    allowing users to implement such tasks and
    takes one or more connectors as dependencies.
 */
public abstract class DataPipeline {
  // TODO: Handle when out-of-range dates were provided.
  // TODO: Handle when dst folder does not exist yet (2024).
  // TODO: Handle when dst data file name is already taken.
  // TODO: Implement logging.
  protected final Map<Class<? extends DataEntity>, List<? extends DataEntity>> incomingDataPack = new HashMap<>();
  protected DataConnector dstDataConnector;
  protected LocalDate targetDate;
  protected final ZonedDateTime executionTimestamp = ZonedDateTime.now();

  public void flushAll(LocalDate startDate, LocalDate endDate) {
    startDate.datesUntil(endDate, Period.ofMonths(1)).forEach(targetDate -> {
      this.targetDate = targetDate.withDayOfMonth(1);
      System.out.println("Flushing " + this.getClass().getSimpleName() + " for date: " + this.targetDate);

      try {
        loadData(transformData(extractData()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    });
  }

  /*
    Preserve the original data type by using wildcard.
    Limit the wildcard by upper bound.
    Provide supertype interface by using bounded wildcard.
    Enforce type matching between K and V within the pair - NOT enabled.
   */
  protected abstract Map<Class<? extends DataEntity>, List<? extends DataEntity>> extractData()
      throws IOException;

  protected abstract List<? extends DataEntity> transformData(
      Map<Class<? extends DataEntity>, List<? extends DataEntity>> incomingData);

  private <T extends DataEntity> void loadData(List<T> transformedData) throws IOException {
    // TODO: It should be okay to pass in non-slash-trailing file path in config.
    URL dataFileLocation = new URL(
        this.dstDataConnector.getLocation(),
        this.targetDate.getYear()
            + "/" + this.targetDate.format(AppConfig.TARGET_DATE_FORMATTER)
            + "." + this.dstDataConnector.getDataReader().getDataFormat().getExtension());

    // TODO: First get FileSystem and then get the File.
    // TODO: Handle when dst data folder does not exists.
    // TODO: Handle when dst data file already exists.
    try(OutputStream stream = new FileOutputStream(dataFileLocation.getPath())) {
      DataWriter writer = this.dstDataConnector.getDataWriter();
      writer.writeData(stream, transformedData);
    }
  }

}
