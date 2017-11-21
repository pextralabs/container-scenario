package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;
import co.pextra.model2.IntrinsicContext;
import co.pextra.model2.Reading;

public class Temperature extends IntrinsicContext<Reading<Double>> {
    public Temperature(String id, Entity bearer) {
        super(id, bearer);
    }

    @Override
    public String toString() {
        return bearer.toString() + " Temperature: " + getValue() + " degrees";
    }
}
