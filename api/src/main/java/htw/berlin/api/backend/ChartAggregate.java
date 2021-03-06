package htw.berlin.api.backend;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.util.Date;

public class ChartAggregate {

    private final int chartId;
    private final String studentId;
    private final ChartLabel chartLabel;
    private final ChartType chartType;
    private final Date createdAt;
    private final Date lastUpdate;
    private final DataSummery dataSummery;
    private final ServiceAddresses serviceAddresses;


    public ChartAggregate() {
        chartId = 0;
        studentId = null;
        chartLabel = null;
        chartType = null;
        createdAt = null;
        lastUpdate = null;
        dataSummery = null;
        serviceAddresses = null;

    }

    public ChartAggregate(int chartId,String studentId, ChartLabel chartLabel, ChartType chartType, Date createdAt, Date lastUpdate, DataSummery dataSummery, ServiceAddresses serviceAddresses) {
        this.chartId = chartId;
        this.studentId = studentId;
        this.chartLabel = chartLabel;
        this.chartType = chartType;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.dataSummery = dataSummery;
        this.serviceAddresses = serviceAddresses;
    }

    public int getChartId() {
        return chartId;
    }

    public String getStudentId() {
        return studentId;
    }

    public ChartLabel getChartLabel() {
        return chartLabel;
    }

    public ChartType getChartType() {
        return chartType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public DataSummery getDataSummery() {
        return dataSummery;
    }

    public ServiceAddresses getServiceAddresses() {
        return serviceAddresses;
    }
}
