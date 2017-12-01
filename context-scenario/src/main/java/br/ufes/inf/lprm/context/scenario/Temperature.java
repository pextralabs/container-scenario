package br.ufes.inf.lprm.context.scenario;

import br.ufes.inf.lprm.context.Entity;
import br.ufes.inf.lprm.context.IntrinsicContext;
import br.ufes.inf.lprm.context.Reading;

public class Temperature extends IntrinsicContext<Reading<Double>> {
    public Temperature(String id, Entity bearer) {
        super(id, bearer);
    }

    @Override
    public String toString() {
        return bearer.toString() + " Temperature: " + getValue() + " degrees";
    }
}
