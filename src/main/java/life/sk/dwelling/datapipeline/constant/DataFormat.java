package life.sk.dwelling.datapipeline.constant;

public enum DataFormat {
  CSV("csv", "csv");

  private final String name;
  private final String extension;
  DataFormat(String name, String extension) {
    this.name = name;
    this.extension = extension;
  }

  public String getName() {
    return name;
  }

  public String getExtension() {
    return extension;
  }
}
