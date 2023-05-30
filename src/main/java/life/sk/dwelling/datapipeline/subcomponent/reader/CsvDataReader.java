package life.sk.dwelling.datapipeline.subcomponent.reader;

import life.sk.dwelling.datapipeline.component.entity.DataEntity;
import life.sk.dwelling.datapipeline.constant.DataFormat;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataReader extends DataReader {

  private static final DataFormat dataFormat = DataFormat.CSV;
  // Note: CSV reader supports file system and s3 but not SQL and no-SQL database.
  // TODO: Figure out incoming data field names mapping to the Bean property names.
  private boolean isFirstLine = true;
  private List<String> fieldNames;
  // Unable to put anything into the Map<String, ?> because it cannot evaluate the subtype of ?.
  private final Map<String, Object> namedRecord = new HashMap<>();

  // TODO: Update this method to take stream object rather than a path.
  public <T extends DataEntity> List<T> readData(InputStream stream, Class<T> klass) {
    List<T> incomingData = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
      Constructor<T> entityConstructor = klass.getDeclaredConstructor();
      String line;
      while ((line = reader.readLine()) != null) {
        // TODO: Utilize "do" block?
        if (isFirstLine) {
          fieldNames = Arrays.asList(line.split(","));
          isFirstLine = false;

        } else {
          // TODO: Optimize performance.
          // TODO: Print/store metadata / logging.
          String[] values = line.split(",");
          for (int i = 0; i< values.length; i++) {
            namedRecord.put(
                fieldNames.get(i),
                klass.getDeclaredField(fieldNames.get(i))
                    .getType()
                    .cast(values[i]));
          }
          // TODO: Put timestamp.
          // Must throw exception if the field does not exist in the incoming data.
          T recordObject = entityConstructor.newInstance();
          for (Field field : klass.getDeclaredFields()) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            Method setter = klass.getDeclaredMethod(
                "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1),
                fieldType);

            setter.invoke(recordObject, namedRecord.get(fieldName));  // Record is already type cast.
          }
          System.out.println(recordObject);
          incomingData.add(recordObject);

          namedRecord.clear();
        }
      }

    } catch(IOException e) {
      System.out.println("Input Data Error");
      throw new RuntimeException(e);

    } catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException |
            NoSuchFieldException | InstantiationException e) {
      System.out.println("Reification Error");
      throw new RuntimeException(e);
    }

    return incomingData;
  }

  public DataFormat getDataFormat() {
    return dataFormat;
  }

}
