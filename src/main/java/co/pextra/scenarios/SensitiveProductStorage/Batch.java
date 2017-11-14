package co.pextra.scenarios.SensitiveProductStorage;

public class Batch {
    private Container container;
    private ProductType productType;
    private Person owner;

    public Batch() {}

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
}
