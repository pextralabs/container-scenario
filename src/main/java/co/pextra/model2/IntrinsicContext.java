package co.pextra.model2;

public abstract class IntrinsicContext<T> {
    protected Entity bearer;
    protected T value;
    protected String id;

    public IntrinsicContext(String id, Entity bearer) {
        this.id = id;
        this.bearer = bearer;
    }

    public String getId() {
        return id;
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
}
