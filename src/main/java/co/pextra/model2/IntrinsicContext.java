package co.pextra.model2;

public abstract class IntrinsicContext<T> {
    protected Entity bearer;
    protected Reading<T> reading;
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
    public Reading<T> getReading() {
        return reading;
    }
    public void setReading(Reading<T> value) {
        this.reading = value;
    }
    public void setReading(T value) {
        this.reading = new Reading<>(value, this.bearer.id, this.id);
    }
    public T getValue() {
        return reading == null ? null : reading.getValue();
    }
}
