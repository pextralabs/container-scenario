package co.pextra.scenarios.SensitiveProductStorage2;

import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import co.pextra.model2.Reading;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BatchTest extends SessionTest{
    static private final Logger LOG = LoggerFactory.getLogger(BatchTest.class);

    @Test
    public void instantiation () {
        ProductType productType = new ProductType("Marijuana", 15.0, 5.0);
        Batch batch = new Batch("batch", productType);

        Assert.assertEquals(batch.getProductType(), productType);
        Assert.assertTrue(batch.getTtt().getValue() == Long.MAX_VALUE);
    }

    @Test
    public void ttt() {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.addEventListener(new SCENESessionListener());
        session.setGlobal("clock", clock);

        LOG.info("Now running data");

        ProductType productType = new ProductType("Marijuana", 15.0, 5.0);
        Batch batch = new Batch("batch1", productType);
        LatLng vix = new LatLng(-20.2976178, 40.2957768);
        Container container = new Container("container", batch);
        container.getLocation().setReading(vix);
        container.getTemperature().setReading(10.0);
        batch.setContainer(container);

        session.insert(productType);
        session.insert(batch);
        batch.getIntrinsicContexts().forEach(session::insert);
        session.insert(container);
        container.getIntrinsicContexts().forEach(session::insert);

        session.fireAllRules();


        long initialTime = clock.getCurrentTime();
        int aux = 0;
        while (clock.getCurrentTime() < initialTime + TimeUnit.MINUTES.toMillis(30)) {
            clock.advanceTime(5, TimeUnit.MINUTES);
            session.insert(new Reading<>(10.0 + 0.5 * ++aux, "container", "temperature", clock.getCurrentTime()));
            session.fireAllRules();
        }
        System.out.println(new Date(clock.getCurrentTime()));
    }
}
