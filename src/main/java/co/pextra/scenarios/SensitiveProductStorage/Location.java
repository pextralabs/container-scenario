package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Reading;

public class Location implements Context<GPSReading> {
    private GPSReading value;
    private Person bearer;
    private String id;

    public Location(String id) {
        this.id = id;
    }

    public Location(String id, Person bearer, Double latitude, Double longitude) {
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
    public Person getBearer() {
        return bearer;
    }

    public void setBearer(Person bearer) {
        this.bearer = bearer;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Latitude: " + value.getLatitude() + "\nLongitude: " + value.getLongitude();
    }

    public boolean near(Location loc, Double distance) {
        Double earthRadius = (6.37814) * Math.pow(10, 6); //earth radius in meters
        Double dLat = Math.toRadians(loc.value.getLatitude() 	- this.value.getLatitude() );
        Double dLng = Math.toRadians(loc.value.getLongitude() - this.value.getLongitude());
        Double sindLat = Math.sin(dLat / 2);
        Double sindLng = Math.sin(dLng / 2);
        Double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(this.value.getLatitude()) * Math.cos(loc.value.getLongitude());
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double dist = earthRadius * c;
        return dist < distance;
    }
}
