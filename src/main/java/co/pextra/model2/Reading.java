package co.pextra.model2;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("10m")
public class Reading<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private long executionTime;
    private T value;
    private String entityId;
    private String contextId;

    public Reading(T value, String contextId) {
        this.value = value;
        this.contextId = contextId;
        this.executionTime = new Date().getTime();
    }
    public Reading(T value, String contextId, long executionTime) {
        this.value = value;
        this.contextId = contextId;
        this.executionTime = executionTime;
    }


    public Reading(T value, String entityID, String contextID) {
        this.value = value;
        this.entityId = entityID;
        this.contextId = contextID;
        this.executionTime = new Date().getTime();
    }

    public Reading(T value, String entityId, String contextId, long executionTime) {
        this.value = value;
        this.entityId = entityId;
        this.contextId = contextId;
        this.executionTime = executionTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public T getValue() {
        return value;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getContextId() {
        return contextId;
    }

    @Override
    public String toString() {
        return "Reading: " + value.toString() + ", from Entity: '" + entityId + "' and Context: '" + contextId + "'";
    }
}
