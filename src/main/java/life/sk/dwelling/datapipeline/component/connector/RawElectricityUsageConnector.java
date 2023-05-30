package life.sk.dwelling.datapipeline.component.connector;

import life.sk.dwelling.datapipeline.subcomponent.reader.CsvDataReader;

import java.net.URL;

public class RawElectricityUsageConnector extends DataConnector {
  public RawElectricityUsageConnector(URL dataLocation) {
    super(new CsvDataReader(), null, dataLocation);
  }
}
