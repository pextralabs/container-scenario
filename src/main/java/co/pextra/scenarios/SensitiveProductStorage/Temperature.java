package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;
import org.kie.api.time.SessionClock;

public class Temperature implements Context<TemperatureReading> {
    private TemperatureReading value;
    private String id;
    private Entity bearer;

    public Temperature(String id, Entity bearer, double initialTemperature) {
        this.id = id;
        this.bearer = bearer;
        this.value = new TemperatureReading(bearer.getID(), id, initialTemperature);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Entity getBearer() {
        return bearer;
    }

    @Override
    public TemperatureReading getValue() {
        return value;
    }

    @Override
    public void setValue(TemperatureReading value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return bearer.toString() + " Temperature: " + value.getValue() + " degrees";
    }

    static public TemperatureReading newReading (String bearerID, String contextID, Double value, SessionClock clock) {
        return new TemperatureReading(bearerID, contextID, value, clock);
    }
}
