package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;

import java.util.Date;

public class EstimateTimeOfArrival implements Context<Date>{
    private Date value;
    private Person bearer;
    private String id;

    public EstimateTimeOfArrival(Person person, String id) {
        this.bearer = person;
        this.id = id;
        this.value = new Date(Long.MAX_VALUE);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Entity getBearer() {
        return bearer;
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public void setValue(Date value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return bearer + " ETA: " + value + "s";
    }
}
