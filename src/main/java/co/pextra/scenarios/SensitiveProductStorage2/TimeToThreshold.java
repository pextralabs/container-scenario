package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;
import co.pextra.model2.IntrinsicContext;
import co.pextra.model2.Reading;

import java.util.Date;
import java.util.List;

public class TimeToThreshold extends IntrinsicContext<Long> {
    public TimeToThreshold(String id, Entity bearer) {
        super(id, bearer);
        setReading(Long.MAX_VALUE);
    }
    @Override
    public String toString() {
        return bearer + " TTT: " + new Date(getValue());
    }

    static public long computeTTT (List<Reading<Double>> readings, ProductType productType) {
        if (readings.size() < 2) return Long.MAX_VALUE;
        else {
            Reading<Double> temp1 = (readings.get(0));
            Reading<Double> temp2 = (readings.get(1));
            double m = (temp2.getValue() - temp1.getValue()) / (temp2.getExecutionTime() - temp1.getExecutionTime());
            double maxTemperature = productType.getMaxThreshold();
            double maxTime = ((maxTemperature - temp2.getValue()) / m) + temp2.getExecutionTime();
            double minTemperature = productType.getMinThreshold();
            double minTime = ((minTemperature - temp2.getValue()) / m) + temp2.getExecutionTime();
            return (long) (m >= 0 ? maxTime : minTime);
        }
    }
}
