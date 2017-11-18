package co.pextra.scenarios.SensitiveProductStorage;

import java.util.Date;

public class EstimateTimeOfArrival {
    private Date value;
    private String id;
    private Person watcher;
    private  Container target;

    public EstimateTimeOfArrival(String id, Person watcher, Container target) {
        this.id = id;
        this.value = new Date(Long.MAX_VALUE);
        this.watcher = watcher;
        this.target = target;
    }

    @Override
    public String toString() {
        return " ETA: " + value + "s";
    }

    public String getId() {
        return id;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public Person getWatcher() {
        return watcher;
    }

    public void setWatcher(Person watcher) {
        this.watcher = watcher;
    }

    public Container getTarget() {
        return target;
    }

    public void setTarget(Container target) {
        this.target = target;
    }
}
