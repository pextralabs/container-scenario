package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import java.util.Date;

public class TimeToThreshold implements Context<Date> {
    private Date value;
    private Batch bearer;
    private String id;

    public TimeToThreshold(String id, Batch bearer) {
        this.bearer = bearer;
        this.id = id;
        this.value = new Date(Long.MAX_VALUE);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Batch getBearer() {
        return bearer;
    }

    public void setBearer(Batch bearer) {
        this.bearer = bearer;
    }

    @Override
    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return bearer + " TTT: " + value;
    }
}
