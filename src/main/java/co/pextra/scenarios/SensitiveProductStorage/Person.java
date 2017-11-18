package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;
import org.kie.api.time.SessionClock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person implements Entity {
    private String name;
    private Location location;
    private Speed speed;
    private EstimateTimeOfArrival eta;
    private List<Container> containers;
    private List<Batch> batches;

    public Person(String name) {
        this.name = name;
        this.location = new Location(this.name + "-location", this);
        location.setBearer(this);
        this.speed = new Speed(this, this.name + "-speed");
        this.eta = new EstimateTimeOfArrival(this, this.name + "-eta");
        this.containers = new ArrayList<>();
        this.batches = new ArrayList<>();
    }

    public Person(String name, double latitude, double longitude) {
        this.name = name;
        this.location = new Location(this.name + "-location", this, latitude, longitude);
        this.speed = new Speed(this, this.name + "-speed");
        this.eta = new EstimateTimeOfArrival(this, this.name + "-eta");
        this.containers = new ArrayList<>();
        this.batches = new ArrayList<>();
    }

    static public GPSReading walk(Person person, double x, double y) {
        double earthRadius = (6.37814) * Math.pow(10, 6); //earth radius in meters
        GPSReading cur = person.location.getValue();
        double nextLatitude = cur.getLatitude() + (y / earthRadius) * (180 / Math.PI);
        double nextLongitude = cur.getLongitude() + (x / earthRadius) * (180 / Math.PI) / Math.cos(cur.getLatitude() * Math.PI / 180);
        return new GPSReading(person, person.location, nextLatitude, nextLongitude);
    }

    static public GPSReading walk(Person person, double x, double y, SessionClock clock) {
        double earthRadius = (6.37814) * Math.pow(10, 6); //earth radius in meters
        GPSReading cur = person.location.getValue();
        double nextLatitude = cur.getLatitude() + (y / earthRadius) * (180 / Math.PI);
        double nextLongitude = cur.getLongitude() + (x / earthRadius) * (180 / Math.PI) / Math.cos(cur.getLatitude() * Math.PI / 180);
        return new GPSReading(person, person.location, nextLatitude, nextLongitude, clock);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    @Override
    public String getID() {
        return this.name;
    }

    @Override
    public List<Context> getContexts() {
        return Arrays.asList(location, speed, eta);
    }

}
