package br.ufes.inf.lprm.context.scenario;

import br.ufes.inf.lprm.context.model.ContextValue;
import br.ufes.inf.lprm.context.model.Entity;
import br.ufes.inf.lprm.context.model.IntrinsicContext;

import java.util.ArrayList;
import java.util.List;

public class Speed extends IntrinsicContext<Double> {
    static public double MIN_SPEED = 6.0;
    static public double MAX_SPEED = Double.MAX_VALUE;
    public Speed (String id, Entity bearer) {
        super(id, bearer);
    }

    @Override
    public String toString() {
        return "Bearer( " + bearer + " )" + " Speed: " + getContextValue() + " m/s";
    }

    static public Double computeSpeed(List<ContextValue<LatLng>> readings){
        if (readings.size() < 2) return MIN_SPEED;
        else {
            ArrayList<Double> speeds = new ArrayList<>();
            for(int i = 0; i < readings.size() - 1; i++) {
                ContextValue<LatLng> curr = readings.get(i);
                ContextValue<LatLng> next = readings.get(i + 1);
                double ds = Location.distance(next.getValue(), curr.getValue());
                long dt = (next.getTimestamp() - curr.getTimestamp()) / 1000;
                speeds.add(ds/dt);
            }
            Double avg = speeds.stream().reduce(0.0, (a, b) -> a + b) / speeds.size();
            double speed = Math.round(avg*100.0)/100.0;
            if (speed > MAX_SPEED) return MAX_SPEED;
            if (speed < MIN_SPEED) return MIN_SPEED;
            return speed;
        }
    }
}
