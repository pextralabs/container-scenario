package br.ufes.inf.lprm.context.model;

import java.util.List;
import java.util.Set;

public abstract class RelationalContext {
    protected String UID;
    protected Set<Entity> entities;
    public RelationalContext(String UID, Set<Entity> entities) {
        this.UID = UID;
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
}
