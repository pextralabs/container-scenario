package br.ufes.inf.lprm.context.scenario;

import br.ufes.inf.lprm.context.model.ContextValue;
import br.ufes.inf.lprm.context.model.Entity;
import br.ufes.inf.lprm.context.model.IntrinsicContext;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

public class TimeToThreshold extends IntrinsicContext<Long> {
    static public long MIN_VALUE = 0;
    static public long MAX_VALUE = Long.MAX_VALUE;
    public TimeToThreshold(String id, Entity bearer) {
        super(id, bearer);
    }
    @Override
    public String toString() {
        return bearer + " TTT: " + getContextValue();
    }
    static public long computeTTT (List<ContextValue<Double>> readings, ProductType productType) {
        return computeTTT(readings, productType, LocalDateTime.now());
    }
    static public long computeTTT (List<ContextValue<Double>> readings, ProductType productType, LocalDateTime now) {
        if (readings.size() < 2) return MAX_VALUE;
        else {
            ContextValue<Double> temp1 = (readings.get(readings.size() - 2));
            ContextValue<Double> temp2 = (readings.get(readings.size() - 1));
            double m = (temp2.getValue() - temp1.getValue()) / (temp2.getTimestamp() - temp1.getTimestamp());
            double maxTemperature = productType.getMaxThreshold();
            double maxTime = ((maxTemperature - temp2.getValue()) / m) + temp2.getTimestamp();
            double minTemperature = productType.getMinThreshold();
            double minTime = ((minTemperature - temp2.getValue()) / m) + temp2.getTimestamp();
            long time = (long) (m >= 0 ? maxTime : minTime);
            long ttt = Duration
                    .between(
                            now,
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone.getDefault().toZoneId())
                    )
                    .getSeconds();
            if (ttt > MAX_VALUE) return  MAX_VALUE;
            if (ttt < MIN_VALUE) return  MIN_VALUE;
            return ttt;
        }
    }
}
