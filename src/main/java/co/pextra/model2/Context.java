package co.pextra.model2;

public abstract class Context<T> {
    protected Reading<T> reading;
    protected String id;

    public Context(String id) {
        this.id = id;
        this.reading = new Reading<>(null, id);
    }

    public Context(String id, T initialValue) {
        this.id = id;
        this.reading = new Reading<>(initialValue, id);
    }

    public String getId() {
        return id;
    }
    public Reading<T> getReading() {
        return reading;
    }
    public void setReading(Reading<T> value) {
        this.reading = value;
    }
    public void setReading(T value) {
        this.reading = new Reading<>(value, this.id);
    }
    public T getValue() {
        return reading == null ? null : reading.getValue();
    }
}
