package br.ufes.inf.lprm.context.model;

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
    private String id;

    public Reading(T value, String contextId) {
        this.value = value;
        this.id = contextId;
        this.executionTime = new Date().getTime();
    }
    public Reading(T value, String contextId, long executionTime) {
        this.value = value;
        this.id = contextId;
        this.executionTime = executionTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public T getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Reading: " + value.toString() + ", from: '" + id + "'";
    }
}
