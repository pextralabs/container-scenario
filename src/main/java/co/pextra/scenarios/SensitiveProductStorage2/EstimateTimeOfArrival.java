package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;
import co.pextra.model2.RelationalContext;

public class EstimateTimeOfArrival extends RelationalContext<Long>{

    public EstimateTimeOfArrival(String id, Entity... entities) {
        super(id, entities);
    }

    public EstimateTimeOfArrival(String id, Long initialValue, Entity... entities) {
        super(id, initialValue, entities);
    }

    @Override
    public String toString() {
        return "ETA: " + getValue() + "s";
    }

    static  public long computeETA(Person p, Container c) {
        return computeETA(p.getLocation().getValue(), p.getSpeed().getValue(), c.getLocation().getValue());
    }
    static public long computeETA (LatLng pl, double ps, LatLng cl) {
        double distance = Location.distance(pl, cl);
        return (long) (distance / ps);
    }
}
