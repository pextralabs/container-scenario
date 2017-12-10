package br.ufes.inf.lprm.context.model;

public abstract class IntrinsicContext<T> {
    protected String UID;
    protected Entity bearer;
    protected T value;

    public IntrinsicContext(String UID, Entity bearer, T value) {
        this.UID = UID;
        this.bearer = bearer;
        this.value = value;
    }

    public IntrinsicContext(String UID, Entity bearer) {
        this.UID = UID;
        this.bearer = bearer;
    }

    public String getUID() {
        return UID;
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

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
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
