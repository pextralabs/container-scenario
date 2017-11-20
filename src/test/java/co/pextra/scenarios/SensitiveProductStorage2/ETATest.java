package co.pextra.scenarios.SensitiveProductStorage2;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;

public class ETATest extends SessionTest {
    static private final Logger LOG = LoggerFactory.getLogger(ETATest.class);

    @Test
    public void instantiation () {
        LatLng vix = new LatLng(-20.2976178, -40.2957768);
        LatLng vv = new LatLng(-20.347782, -40.294953);
        Person john = new Person("john");
        john.getLocation().setReading(vix);
        john.getSpeed().setReading(10.0);

        ProductType productType = new ProductType("Marijuana", 15.0, 5.0);
        Batch batch = new Batch("batch", productType);

        Container container = new Container("container", batch);
        container.getLocation().setReading(vv);

        Watch watcher = new Watch("for the watch");
        watcher.setWatcher(john);
        watcher.setTarget(batch);
        watcher.setEta(new EstimateTimeOfArrival("for the watch eta"));
    }
}
