package life.sk.dwelling.datapipeline.subcomponent.writer;

import life.sk.dwelling.datapipeline.component.entity.DataEntity;
import life.sk.dwelling.datapipeline.constant.DataFormat;

import java.io.OutputStream;
import java.util.List;

public abstract class DataWriter {
    private DataFormat dataFormat;
    public abstract <T extends DataEntity> void writeData(OutputStream stream, List<T> transformedData);

    public DataFormat getDataFormat() {
        return dataFormat;
    }
}
