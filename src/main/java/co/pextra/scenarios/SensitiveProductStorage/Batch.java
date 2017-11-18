package co.pextra.scenarios.SensitiveProductStorage;

import co.pextra.model.Context;
import co.pextra.model.Entity;

import java.util.Arrays;
import java.util.List;

public class Batch implements Entity {
    private Container container;
    private ProductType productType;
    private Person owner;
    private String id;
    private  TimeToThreshold ttt;

    public Batch(String id, Container container, ProductType productType, Person owner) {
        this.container = container;
        this.productType = productType;
        this.owner = owner;
        this.id = id;
        ttt = new TimeToThreshold("ttt-" + id, this);
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public TimeToThreshold getTtt() {
        return ttt;
    }

    public void setTtt(TimeToThreshold ttt) {
        this.ttt = ttt;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public List<Context> getContexts() {
        return Arrays.asList(ttt);
    }

    @Override
    public String toString() {
        return "Batch of " + productType.getName() + " '" + id + "'";
    }
}
