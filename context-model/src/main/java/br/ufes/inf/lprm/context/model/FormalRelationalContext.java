package br.ufes.inf.lprm.context.model;

import java.util.Set;

public abstract class FormalRelationalContext<T> extends  RelationalContext {
    protected ContextValue<T> contextValue;

    public FormalRelationalContext(String UID, Set<Entity> entities) {
        super(UID, entities);
    }
    public ContextValue<T> getContextValue() {
        return contextValue;
    }

    public T getValue() {
        return  contextValue.getValue();
    }

    public void setContextValue(ContextValue<T> latestContextValue) {
        if (latestContextValue.getContextUID().equals(UID)) this.contextValue = latestContextValue;
    }
}
