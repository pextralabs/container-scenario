package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;
import co.pextra.model2.IntrinsicContext;
import co.pextra.model2.Reading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Speed extends IntrinsicContext<Double>{

    public Speed(String id, Entity bearer) {
        super(id, bearer);
        this.setValue(0.0);
    }

    @Override
    public String toString() {
        return "Bearer( " + bearer + " )" + " Speed: " + getValue() + " m/s";
    }

    static public Double computeSpeed(List<Reading<LatLng>> readings){
        if (readings.size() < 2) return 0.0;
        else {
            ArrayList<Double> speeds = new ArrayList<>();
            for(int i = 0; i < readings.size() - 1; i++) {
                Reading<LatLng> curr = readings.get(i);
                Reading<LatLng> next = readings.get(i + 1);
                double ds = Location.distance(next.getValue(), curr.getValue());
                long dt = (next.getExecutionTime() - curr.getExecutionTime()) / 1000;
                speeds.add(ds/dt);
            }
            Double avg = speeds.stream().reduce(0.0, (a, b) -> a + b) / speeds.size();
            return Math.round(avg*100.0)/100.0;
        }
    }
}
