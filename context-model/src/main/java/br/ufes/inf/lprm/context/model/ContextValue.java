package br.ufes.inf.lprm.context.model;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("updateTime")
public class ContextValue<T> implements Serializable {
    private static final long serialVersionUID = 2L;
    private long updateTime;
    private T value;
    private String contextUID;

    public ContextValue(T value, String contextUID) {
        this.value = value;
        this.contextUID = contextUID;
        this.updateTime = new Date().getTime();
    }
    public ContextValue(T value, String contextUID, long updateTime) {
        this.value = value;
        this.contextUID = contextUID;
        this.updateTime = updateTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getContextUID() {
        return contextUID;
    }

    public T getValue() {
        return value;
    }
}
