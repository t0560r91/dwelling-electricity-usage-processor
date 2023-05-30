package life.sk.dwelling.datapipeline.component.entity;

import java.sql.Timestamp;

// POJO - JavaBean
// TODO: Need serializeable?
public class RawElectricityUsage extends DataEntity {
    private String accountId;
    private String serviceAddress;
    private String day;
    private String consumptionKwh;
    private String temperatureF;
    private Timestamp ingestionDateTime;

    public RawElectricityUsage() {}

    // TODO: Remove this constructor?
    public RawElectricityUsage(String accountId, String serviceAddress, String day,
                               String consumptionKwh, String temperatureF, Timestamp ingestionDateTime) {
        this.accountId = accountId;
        this.serviceAddress = serviceAddress;
        this.day = day;
        this.consumptionKwh = consumptionKwh;
        this.temperatureF = temperatureF;
        this.ingestionDateTime = ingestionDateTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public String getDay() {
        return day;
    }

    public String getConsumptionKwh() {
        return consumptionKwh;
    }

    public String getTemperatureF() {
        return temperatureF;
    }

    public Timestamp getIngestionDateTime() {
        return ingestionDateTime;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setConsumptionKwh(String consumptionKwh) {
        this.consumptionKwh = consumptionKwh;
    }

    public void setTemperatureF(String temperatureF) {
        this.temperatureF = temperatureF;
    }

    public void setIngestionDateTime(Timestamp ingestionDateTime) {
        this.ingestionDateTime = ingestionDateTime;
    }

    @Override
    public String toString() {
        return "RawElectricityUsage{" +
                "accountId='" + accountId + '\'' +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", day='" + day + '\'' +
                ", consumptionKwh='" + consumptionKwh + '\'' +
                ", temperatureF='" + temperatureF + '\'' +
                ", ingestionDateTime=" + ingestionDateTime +
                '}';
    }
}
