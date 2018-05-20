package br.ufes.inf.lprm.context.model;

public abstract class IntrinsicContext<T> {
    private String UID;
    protected Entity bearer;
    private ContextValue<T> contextValue;

    public IntrinsicContext(String UID, Entity bearer) {
        this.UID = UID;
        this.bearer = bearer;
    }

    public String getUID() {
        return UID;
    }
    public ContextValue<T> createContextValue (T value) {
        return new ContextValue<>(value, UID);
    }
    public void setUID(String id) {
        this.UID = id;
    }

    public Entity getBearer() {
        return bearer;
    }

    public void setBearer(Entity bearer) {
        this.bearer = bearer;
    }

    public ContextValue<T> getContextValue() {
        return contextValue;
    }

    public  T getValue() {
        if (contextValue != null) return contextValue.getValue();
        return  null;
    }

    public void setContextValue(ContextValue<T> value) {
        if (value.getContextUID().equals(UID)) this.contextValue = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntrinsicContext)) return false;

        IntrinsicContext<?> that = (IntrinsicContext<?>) o;

        return getUID().equals(that.getUID());
    }

    @Override
    public int hashCode() {
        return getUID().hashCode();
    }
}
