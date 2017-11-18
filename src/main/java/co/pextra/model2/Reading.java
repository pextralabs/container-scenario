package co.pextra.model2;
import co.pextra.scenarios.SensitiveProductStorage2.LatLng;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.time.SessionClock;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("10m")
public class Reading<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private long executionTime;
    private T value;
    private String entityID;
    private String contextID;

    public Reading(T value, String entityID, String contextID) {
        this.value = value;
        this.entityID = entityID;
        this.contextID = contextID;
        this.executionTime = new Date().getTime();
    }

    public Reading(T value, String entityID, String contextID, SessionClock clock) {
        this.value = value;
        this.entityID = entityID;
        this.contextID = contextID;
        this.executionTime = new Date(clock.getCurrentTime()).getTime();
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public T getValue() {
        return value;
    }

    public String getEntityID() {
        return entityID;
    }

    public String getContextID() {
        return contextID;
    }

    @Override
    public String toString() {
        return "Reading: " + value.toString();
    }
}
