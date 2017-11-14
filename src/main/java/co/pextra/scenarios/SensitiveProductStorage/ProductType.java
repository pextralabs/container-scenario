package co.pextra.scenarios.SensitiveProductStorage;

public class ProductType {
    private  String name;
    private  double maxThreshold;
    private  double minThreshold;

    public ProductType(String name, double maxTemp) {
        this.name = name;
        this.maxThreshold = maxTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxThreshold() {
        return maxThreshold;
    }

    public void setMaxThreshold(double maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public double getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(double minThreshold) {
        this.minThreshold = minThreshold;
    }
}
