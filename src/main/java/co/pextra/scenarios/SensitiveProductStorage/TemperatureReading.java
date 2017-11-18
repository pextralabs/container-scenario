package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Reading;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.time.SessionClock;

import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("10m")
public class TemperatureReading implements Reading {
    private Double value;
    private Date executionTime;
    private String contextID;
    private  String bearerID;

    public TemperatureReading(String bearerID, String contextID, Double value) {
        this.value = value;
        this.bearerID = bearerID;
        this.contextID = contextID;
        this.executionTime = new Date();
    }
    public TemperatureReading(String bearerID, String contextID, Double value, SessionClock clock) {
        this.value = value;
        this.bearerID = bearerID;
        this.contextID = contextID;
        this.executionTime = new Date(clock.getCurrentTime());
    }


    @Override
    public String getBearerID() {
        return bearerID;
    }

    @Override
    public String getContextID() {
        return contextID;
    }

    @Override
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }
}
