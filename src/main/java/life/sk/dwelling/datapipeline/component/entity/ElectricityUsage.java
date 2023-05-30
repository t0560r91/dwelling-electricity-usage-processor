package life.sk.dwelling.datapipeline.component.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class ElectricityUsage extends DataEntity {
    private String accountId;
    private String serviceAddress;
    private float consumptionKwh;
    private int temperatureF;
    private Date meteredDate;
    private Timestamp ingestionDateTime;
    private Timestamp processingDateTime;

    public ElectricityUsage(String accountId, String serviceAddress, float consumptionKwh,
                            int temperatureF, Date meteredDate,
                            Timestamp ingestionDateTime, Timestamp processingDateTime) {

        this.accountId = accountId;
        this.serviceAddress = serviceAddress;
        this.consumptionKwh = consumptionKwh;
        this.temperatureF = temperatureF;
        this.meteredDate = meteredDate;
        this.ingestionDateTime = ingestionDateTime;
        this.processingDateTime = processingDateTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public float getConsumptionKwh() {
        return consumptionKwh;
    }

    public int getTemperatureF() {
        return temperatureF;
    }

    public Date getMeteredDate() {
        return meteredDate;
    }

    public Timestamp getIngestionDateTime() {
        return ingestionDateTime;
    }

    public Timestamp getProcessingDateTime() {
        return processingDateTime;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public void setConsumptionKwh(float consumptionKwh) {
        this.consumptionKwh = consumptionKwh;
    }

    public void setTemperatureF(int temperatureF) {
        this.temperatureF = temperatureF;
    }

    public void setMeteredDate(Date meteredDate) {
        this.meteredDate = meteredDate;
    }

    public void setIngestionDateTime(Timestamp ingestionDateTime) {
        this.ingestionDateTime = ingestionDateTime;
    }

    public void setProcessingDateTime(Timestamp processingDateTime) {
        this.processingDateTime = processingDateTime;
    }

    @Override
    public String toString() {
        return "ElectricityUsage{" +
            "accountId=" + accountId +
            ", serviceAddress='" + serviceAddress + '\'' +
            ", consumptionKwh=" + consumptionKwh +
            ", temperatureF=" + temperatureF +
            ", meteredDate=" + meteredDate +
            ", ingestionDateTime=" + ingestionDateTime +
            ", processingDateTime=" + processingDateTime +
            '}';
    }
}
