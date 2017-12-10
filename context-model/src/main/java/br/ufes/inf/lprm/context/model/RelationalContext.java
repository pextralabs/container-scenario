package br.ufes.inf.lprm.context.model;

import java.util.List;
import java.util.Set;

public abstract class RelationalContext<T> {
    protected String UID;
    protected Set<Entity> entities;
    protected T value;

    public RelationalContext(String UID, Set<Entity> entities) {
        this.UID = UID;
        this.entities = entities;
    }

    public RelationalContext(String UID, T value, Set<Entity> entities) {
        this.UID = UID;
        this.value = value;
        this.entities = entities;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationalContext)) return false;

        RelationalContext that = (RelationalContext) o;

        return getUID().equals(that.getUID());
    }

    @Override
    public int hashCode() {
        return getUID().hashCode();
    }

    public static RelationalContextInsert makeInsert(Class<? extends  RelationalContext> contextClass, String contextUID, List<String> entitiesID, long time) {
        return  new RelationalContextInsert(contextClass, contextUID, entitiesID, time);
    }
    public static RelationalContextInsert makeInsert(Class<? extends  RelationalContext> contextClass, String contextUID, List<String> entitiesID, Object value, long time) {
        return  new RelationalContextInsert(contextClass, contextUID, entitiesID, value, time);
    }
}
