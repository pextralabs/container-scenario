package br.ufes.inf.lprm.context.model;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("retractionTime")
public class RelationalContextDelete {
    private String contextUID;
    private long retractionTime;

    public RelationalContextDelete(String contextUID) {
        this.contextUID = contextUID;
        retractionTime = new Date().getTime();
    }
    public RelationalContextDelete(String contextUID, long retractionTime) {
        this.contextUID = contextUID;
        this.retractionTime = retractionTime;
    }

    public String getContextUID() {
        return contextUID;
    }

    public long getRetractionTime() {
        return retractionTime;
    }
}