package co.pextra.model;

public interface Context<T> {
    String getID();
    Entity getBearer();

    T getValue();
    void setValue(T value);
}
