package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Person implements Entity {
    private String name;
    private Location location;
    private Speed speed;
    private List<Container> containers;

    public Person(String name) {
        this.name = name;
        this.location = new Location(this.name + "-location", this, 0.0, 0.0);
        this.speed = new Speed(this, this.name + "-speed");
        this.containers = new ArrayList<Container>();
    }

    public Person(String name, Location location) {
        this.name = name;
        this.location = location;
        location.setBearer(this);
        this.speed = new Speed(this, this.name + "-speed");
        this.containers = new ArrayList<Container>();
    }

    public Person(String name, double latitude, double longitude) {
        this.name = name;
        this.location = new Location(this.name + "-location", this, latitude, longitude);
        this.speed = new Speed(this, this.name + "-speed");
        this.containers = new ArrayList<Container>();
    }

    static public GPSReading walk(Person person, double x, double y) {
        double earthRadius = (6.37814) * Math.pow(10, 6); //earth radius in meters
        GPSReading cur = person.location.getValue();
        double nextLatitude = cur.getLatitude() + (y / earthRadius) * (180 / Math.PI);
        double nextLongitude = cur.getLongitude() + (x / earthRadius) * (180 / Math.PI) / Math.cos(cur.getLatitude() * Math.PI / 180);
        return new GPSReading(person, person.location, nextLatitude, nextLongitude);
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

    @Override
    public String getID() {
        return this.name;
    }

    @Override
    public List<Context> getContexts() {
        ArrayList<Context> contexts = new ArrayList<Context>();
        contexts.add(location);
        return contexts;
    }
}
