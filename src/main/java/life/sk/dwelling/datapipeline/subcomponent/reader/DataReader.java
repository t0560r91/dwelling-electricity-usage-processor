package life.sk.dwelling.datapipeline.subcomponent.reader;

import life.sk.dwelling.datapipeline.component.entity.DataEntity;
import life.sk.dwelling.datapipeline.constant.DataFormat;

import java.io.InputStream;
import java.util.List;

public abstract class DataReader {
  private static DataFormat dataFormat;
  public abstract <T extends DataEntity> List<T> readData(InputStream stream, Class<T> klass);

  public DataFormat getDataFormat() {
    return dataFormat;
  }

}
