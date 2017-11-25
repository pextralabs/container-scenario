package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;
import co.pextra.model2.RelationalContext;

public class EstimateTimeOfArrival extends RelationalContext<Long>{

    public EstimateTimeOfArrival(String id, Entity... entities) {
        super(id, Long.MAX_VALUE, entities);
    }
    public EstimateTimeOfArrival(String id, long initialValue, Entity... entities) {
        super(id, initialValue, entities);
    }


    @Override
    public String toString() {
        return "ETA: " + getValue() + "s";
    }

    @Override
    public boolean equals(Object o) {
        return  o instanceof  EstimateTimeOfArrival && this.id.equals(((EstimateTimeOfArrival) o).id);
    }

    static  public long computeETA(Person p, Container c) {
        return computeETA(p.getLocation().getValue().getValue(), p.getSpeed().getValue(), c.getLocation().getValue().getValue());
    }
    static public long computeETA (LatLng pl, double ps, LatLng cl) {
        double distance = Location.distance(pl, cl);
        return (long) (distance / ps);
    }
}
