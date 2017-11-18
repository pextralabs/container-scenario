package co.pextra.scenarios.SensitiveProductStorage2;

import co.pextra.model2.Entity;

public class Person extends Entity {
    private Location location;
    public Person(String id) {
        super(id);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person: " + id;
    }
}
