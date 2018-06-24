package br.ufes.inf.lprm.context.model;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class ContextValue<T> implements Serializable {
    private static final long serialVersionUID = 2L;
    private long timestamp;
    private T value;
    private String cid;

    public ContextValue(T value, String cid) {
        this.value = value;
        this.cid = cid;
        this.timestamp = new Date().getTime();
    }
    public ContextValue(T value, String cid, long timestamp) {
        this.value = value;
        this.cid = cid;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCid() {
        return cid;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("ContextValue(%s)", value.toString());
    }
}
