package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;

public class Speed implements Context<Double> {
    private Person bearer;
    private Double value = 0.0;
    private String id;
    public Speed(Person bearer, String id) {
        this.bearer = bearer;
        this.id = id;
    }

    @Override
    public Person getBearer() {
        return bearer;
    }

    public void setBearer(Person bearer) {
        this.bearer = bearer;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return bearer.toString() + " Speed: " + value + "m/s";
    }
}
