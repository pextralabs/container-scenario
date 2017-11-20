package co.pextra.model2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RelationalContext<T> extends Context<T> {
    protected Set<Entity> entities;

    public RelationalContext(String id, Entity... entities) {
        super(id);
        this.entities = new HashSet<>(Arrays.asList(entities));
    }

    public RelationalContext(String id, T initialValue, Entity... entities) {
        super(id, initialValue);
        this.entities = new HashSet<>(Arrays.asList(entities));
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }
}
