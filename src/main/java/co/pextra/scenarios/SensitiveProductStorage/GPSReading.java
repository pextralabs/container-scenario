package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;
import co.pextra.model.Reading;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.time.SessionClock;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("10m")
public class GPSReading implements Serializable, Reading {
    private static final long serialVersionUID = 1L;
    private Date executionTime;
    private LatLng value;
    private Context context;
    private Entity bearer;

    public GPSReading(Entity bearer, Context context, double latitude, double longitude) {
        value = new LatLng(latitude, longitude);
        this.executionTime = new Date();
        this.bearer = bearer;
        this.context= context;
    }

    public GPSReading(Entity bearer, Context context, double latitude, double longitude, SessionClock clock) {
        value = new LatLng(latitude, longitude);
        this.executionTime = new Date(clock.getCurrentTime());
        this.bearer = bearer;
        this.context= context;
    }

    public double getLatitude() {
        return value.getLatitude();
    }

    public void setLatitude(double latitude) {
        value.setLatitude(latitude);
    }

    public double getLongitude() {
        return value.getLongitude();
    }

    public void setLongitude(double longitude) {
        value.setLongitude(longitude);
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public LatLng getValue() {
        return value;
    }

    public void setValue(LatLng value) {
        this.value = value;
    }

    public String getContextID() {
        return context.getID();
    }

    public String getBearerID() {
        return bearer.getID();
    }

    @Override
    public String toString() {
        return "Latitude: " + value.getLatitude() + "\nLongitude: " + value.getLongitude();
    }
}
