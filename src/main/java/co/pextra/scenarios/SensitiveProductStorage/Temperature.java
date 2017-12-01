package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Entity;
import co.pextra.model.IntrinsicContext;
import co.pextra.model.Reading;

public class Temperature extends IntrinsicContext<Reading<Double>> {
    public Temperature(String id, Entity bearer) {
        super(id, bearer);
    }

    @Override
    public String toString() {
        return bearer.toString() + " Temperature: " + getValue() + " degrees";
    }
}
