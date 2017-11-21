package co.pextra.model2;

public abstract class Context<T> {
    protected T value;
    protected String id;

    public Context(String id) {
        this.id = id;
        this.value = null;
    }

    public Context(String id, T initialValue) {
        this.id = id;
        this.value = initialValue;
    }

    public String getId() {
        return id;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
}
