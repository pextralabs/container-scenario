package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;

import java.util.Arrays;
import java.util.List;

public class Container implements Entity {
    private Location location;
    private Temperature temperature;
    private Batch batch;
    private String id;
    private Person owner;

    public Container(Person owner, String id, Location location, Temperature temperature) {
        this.owner = owner;
        this.location = location;
        this.temperature = temperature;
        this.id = id;
    }

    public Container(Person owner, String id, double latitude, double longitude, double temperature) {
        this.owner = owner;
        this.location = new Location("location-" + id, this, latitude, longitude);
        this.temperature = new Temperature("temperature-" +  id, this, temperature);
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public List<Context> getContexts() {
        return Arrays.asList(location, temperature);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Container '" + id + "'";
    }
}
