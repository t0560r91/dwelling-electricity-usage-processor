package life.sk.dwelling.datapipeline.subcomponent.writer;

import life.sk.dwelling.datapipeline.component.entity.DataEntity;

import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class CsvDataWriter extends DataWriter {
  @Override
  public <T extends DataEntity> void writeData(OutputStream stream, List<T> transformedData) {
    // TODO: Implement.
  }
}
