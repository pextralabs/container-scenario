package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;

public class Watch extends Entity {
    private Person watcher;
    private Batch target;
    private EstimateTimeOfArrival eta;
    public Watch(String id) {
        super(id);
    }

    public EstimateTimeOfArrival getEta() {
        return eta;
    }

    public void setEta(EstimateTimeOfArrival eta) {
        this.eta = eta;
    }

    public Person getWatcher() {
        return watcher;
    }

    public void setWatcher(Person watcher) {
        this.watcher = watcher;
    }

    public Batch getTarget() {
        return target;
    }

    public void setTarget(Batch target) {
        this.target = target;
    }
}
