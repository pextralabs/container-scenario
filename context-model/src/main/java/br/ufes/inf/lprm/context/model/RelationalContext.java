package br.ufes.inf.lprm.context.model;

import java.util.Set;

public abstract class RelationalContext<T> extends  Context<T>{
    protected Set<Entity> entities;
    public RelationalContext(String cid, Set<Entity> entities) {
        super(cid);
        this.entities = entities;
    }
    public Set<Entity> getEntities() {
        return entities;
    }
    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }
}
