package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;
import co.pextra.model2.IntrinsicContext;

public class EstimateTimeOfArrival {

//    @Override
//    public String toString() {
//        return " ETA: " + getValue() + "s";
//    }

    static  public long computeETA(Person p, Container c) {
        return computeETA(p.getLocation().getValue(), p.getSpeed().getValue(), c.getLocation().getValue());
    }
    static public long computeETA (LatLng pl, double ps, LatLng cl) {
        double distance = Location.distance(pl, cl);
        return (long) (distance / ps);
    }
}
