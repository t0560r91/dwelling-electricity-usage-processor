package life.sk.dwelling.datapipeline.component.connector;

import life.sk.dwelling.datapipeline.subcomponent.reader.DataReader;
import life.sk.dwelling.datapipeline.subcomponent.writer.DataWriter;

import java.net.URL;

/*
  Connectors has two axis:
    - data storage type such as filesystem, s3 bucket, SQL dataabse, no-SQL database, etc.
    - data format type such as CSV, JSON, Parquet, SQL, etc.

  Data storage type is configurable.
  Data format type is NOT configurable.

  If the data requires new data format type, then a new version of the data connector has to be provided.
 */

public abstract class DataConnector {
  // TODO: Elaborate read/write strategies. eg. partitioning.

  // Data format type are hard coded.
  final DataReader dataReader;
  final DataWriter dataWriter;

  // Data storage type is configurable.
  final URL dataLocation;

  public DataConnector(DataReader dataReader, DataWriter dataWriter, URL dataLocation) {
    this.dataReader = dataReader;
    this.dataWriter = dataWriter;
    this.dataLocation = dataLocation;
  }

  public DataReader getDataReader() {
    return dataReader;
  }

  public DataWriter getDataWriter() {
    return dataWriter;
  }

  public URL getLocation() {
    return dataLocation;
  }

}
