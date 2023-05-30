package life.sk.dwelling.datapipeline.config;

import java.time.LocalDate;

public abstract class CommonJobConfig {
  private final LocalDate startDate;
  private final LocalDate endDate;

  public CommonJobConfig(LocalDate startDate, LocalDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }
}
