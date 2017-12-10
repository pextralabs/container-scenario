package br.ufes.inf.lprm.context.model;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;
import java.util.List;

@Role(Role.Type.EVENT)
@Timestamp("insertionTime")
public class RelationalContextInsert {
    private List<String> entitiesUID;
    private  String contextUID;
    private Class<? extends RelationalContext> contextClass;
    private Object value;
    private long insertionTime;

    public RelationalContextInsert(Class<? extends RelationalContext> contextClass, String contextUID, List<String> entitiesUID) {
        this.entitiesUID = entitiesUID;
        this.contextUID = contextUID;
        this.contextClass = contextClass;
        insertionTime = new Date().getTime();
    }
    public RelationalContextInsert(Class<? extends RelationalContext> contextClass, String contextUID, List<String> entitiesUID, long insertionTime) {
        this.entitiesUID = entitiesUID;
        this.contextUID = contextUID;
        this.contextClass = contextClass;
        this.insertionTime = insertionTime;
    }

    public RelationalContextInsert(Class<? extends RelationalContext> contextClass, String contextUID, List<String> entitiesUID, Object value) {
        this.entitiesUID = entitiesUID;
        this.contextUID = contextUID;
        this.contextClass = contextClass;
        this.value = value;
        insertionTime = new Date().getTime();
    }
    public RelationalContextInsert(Class<? extends RelationalContext> contextClass, String contextUID, List<String> entitiesUID, Object value, long insertionTime) {
        this.entitiesUID = entitiesUID;
        this.contextUID = contextUID;
        this.contextClass = contextClass;
        this.value = value;
        this.insertionTime = insertionTime;
    }

    public List<String> getEntitiesUID() {
        return entitiesUID;
    }

    public String getContextUID() {
        return contextUID;
    }

    public Class<? extends RelationalContext> getContextClass() {
        return contextClass;
    }

    public long getInsertionTime() {
        return insertionTime;
    }

    public Object getValue() {
        return value;
    }

    public boolean hasValue () {
        return  value != null;
    }
}