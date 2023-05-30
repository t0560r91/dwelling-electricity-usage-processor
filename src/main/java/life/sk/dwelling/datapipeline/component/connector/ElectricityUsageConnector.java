package life.sk.dwelling.datapipeline.component.connector;

import life.sk.dwelling.datapipeline.subcomponent.reader.CsvDataReader;
import life.sk.dwelling.datapipeline.subcomponent.writer.CsvDataWriter;

import java.net.URL;

public class ElectricityUsageConnector extends DataConnector {

  public ElectricityUsageConnector(URL dataLocation) {
    super(new CsvDataReader(), new CsvDataWriter(), dataLocation);
  }
}
