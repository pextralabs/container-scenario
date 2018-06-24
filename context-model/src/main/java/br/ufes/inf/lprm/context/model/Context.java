package br.ufes.inf.lprm.context.model;

import java.net.URI;

public abstract class Context<T> {
    private String cid;
    private    ContextValue<T> contextValue;
    public Context(String cid) {
        this.cid = cid;
    }
    public ContextValue<T> createContextValue (T value) {
        return new ContextValue<>(value, cid);
    }
    public ContextValue<T> getContextValue() {
        return contextValue;
    }
    public  T getValue() {
        if (contextValue != null) return contextValue.getValue();
        return  null;
    }

    public void setContextValue(ContextValue<T> contextValue) {
        if (contextValue.getCid().equals(cid)) this.contextValue = contextValue;
    }
    public String getCid() {
        return cid;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Context)) return false;

        Context<?> that = (Context<?>) o;

        return cid.equals(that.getCid());
    }

    @Override
    public int hashCode() {
        return cid.hashCode();
    }
}
