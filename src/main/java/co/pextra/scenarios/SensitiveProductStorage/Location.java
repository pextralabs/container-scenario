package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;
import org.kie.api.time.SessionClock;

public class Location implements Context<GPSReading> {
    private GPSReading value;
    private Entity bearer;
    private String id;

    public Location(String id, Entity bearer) {
        this.id = id;
        this.bearer = bearer;
    }

    public Location(String id, Entity bearer, Double latitude, Double longitude) {
        this.value = new GPSReading(bearer, this, latitude, longitude);
        this.bearer = bearer;
        this.id = id;
    }

    @Override
    public GPSReading getValue() {
        return value;
    }

    @Override
    public void setValue(GPSReading value) {
        this.value = value;
    }

    @Override
    public Entity getBearer() {
        return bearer;
    }

    public void setBearer(Entity bearer) {
        this.bearer = bearer;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return bearer.toString() +  " Lat: " + value.getLatitude() + " Lng: " + value.getLongitude();
    }

    public boolean near(Location loc, Double distance) {
        Double dLat = Math.toRadians(loc.value.getLatitude() 	- this.value.getLatitude() );
        Double dLng = Math.toRadians(loc.value.getLongitude() - this.value.getLongitude());
        Double sindLat = Math.sin(dLat / 2);
        Double sindLng = Math.sin(dLng / 2);
        Double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(this.value.getLatitude()) * Math.cos(loc.value.getLongitude());
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double dist = earthRadius * c;
        return dist < distance;
    }

    static public double distance (GPSReading l1, GPSReading l2) {
        double lat1 = l1.getValue().getLatitude();
        double lng1 = l1.getValue().getLongitude();
        double lat2 = l2.getValue().getLatitude();
        double lng2 = l2.getValue().getLongitude();
        double dlat = degreesToRadians(lat1 - lat2);
        double dlng = degreesToRadians(lng1 - lng2);
        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.sin(dlng/2) * Math.sin(dlng/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }
    static public double distance (Location l1, Location l2) {
        return distance(l1.value, l2.value);
    }

    static  public  double degreesToRadians (double degrees) {
        return degrees * Math.PI / 180;
    }

    static public double earthRadius = (6.37814) * Math.pow(10, 6);

    static public GPSReading walk(Location location, double x, double y) {
        double earthRadius = (6.37814) * Math.pow(10, 6); //earth radius in meters
        GPSReading cur = location.getValue();
        double nextLatitude = cur.getLatitude() + (y / earthRadius) * (180 / Math.PI);
        double nextLongitude = cur.getLongitude() + (x / earthRadius) * (180 / Math.PI) / Math.cos(cur.getLatitude() * Math.PI / 180);
        return new GPSReading(location.bearer, location, nextLatitude, nextLongitude);
    }

    static public GPSReading walk(Location location, double x, double y, SessionClock clock) {
        double earthRadius = (6.37814) * Math.pow(10, 6); //earth radius in meters
        GPSReading cur = location.getValue();
        double nextLatitude = cur.getLatitude() + (y / earthRadius) * (180 / Math.PI);
        double nextLongitude = cur.getLongitude() + (x / earthRadius) * (180 / Math.PI) / Math.cos(cur.getLatitude() * Math.PI / 180);
        return new GPSReading(location.bearer, location, nextLatitude, nextLongitude, clock);
    }
}
