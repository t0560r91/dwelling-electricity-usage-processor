package life.sk.dwelling.datapipeline;

import life.sk.dwelling.datapipeline.component.connector.ElectricityUsageConnector;
import life.sk.dwelling.datapipeline.component.connector.RawElectricityUsageConnector;
import life.sk.dwelling.datapipeline.config.JobConfig;

import java.net.MalformedURLException;

// TODO: Streamline passing configs: via AppConfig Constants VS as a config object.
// TODO: Implement logging.
// TODO: Setup workflow.
// TODO: Generalize this entry class by auto-wiring.

// Note: flush is at a given batch (monthly data) level.
// Note: there can be multiple flush in one job.
// Note: job config vs pipeline config.
// Note: pipeline config: configures the logic of the data processing and is accessible from the pipeline level.
  // field name mapping
  // Available via constant?
// Note: job config: configures everything other than the data processing logic and is accessible from the app level.
  // processing data range
  // data path for each environment

public class ElectricityUsageProcessorApp {

  // Decision Note: Peel config values and pass individually rather than as a bundle.
  // because these config values has new context once taking into another class.
  public static void main(String[] args) {
    JobConfig jobConfig = JobConfig.getJobConfig();

    DataPipeline pipeline = new ElectricityUsageProcessorPipeline(
        // (Manual) Dependency Injection.
        // Any other connector dependencies  goes here.
        // However, if we use Spring Framework, we can Autowire instead of manually injecting.
        new RawElectricityUsageConnector(jobConfig.getRawElectricityUsageDataLocation()),
        new ElectricityUsageConnector(jobConfig.getElectricityUsageDataLocation())
    );

    pipeline.flushAll(jobConfig.getStartDate(), jobConfig.getEndDate());

  }
}