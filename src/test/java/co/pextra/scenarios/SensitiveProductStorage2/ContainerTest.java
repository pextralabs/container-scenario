package co.pextra.scenarios.SensitiveProductStorage2;

import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import co.pextra.model2.Reading;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class ContainerTest extends  SessionTest {
    static private final Logger LOG = LoggerFactory.getLogger(ContainerTest.class);

    @Test
    public void instantiation () {
        LatLng vix = new LatLng(-20.2976178, 40.2957768);
        Container container = new Container("container");
        container.getLocation().setReading(vix);
        container.getTemperature().setReading(0.0);

        Assert.assertEquals(container.getBatches().size(), 0);
        Assert.assertEquals(container.getLocation().getValue(), vix);
        Assert.assertEquals(container.getTemperature().getValue(), new Double(0.0));
    }

    @Test
    public void temperature () {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.addEventListener(new SCENESessionListener());

        LOG.info("Now running data");
        LatLng vix = new LatLng(-20.2976178, 40.2957768);
        Container container = new Container("container");
        container.getLocation().setReading(vix);
        container.getTemperature().setReading(0.0);

        session.insert(container);
        container.getIntrinsicContexts().forEach(session::insert);
        session.fireAllRules();

        long initialTime = clock.getCurrentTime();
        int aux = 0;
        while (clock.getCurrentTime() < initialTime + TimeUnit.HOURS.toMillis(2)) {
            clock.advanceTime(30, TimeUnit.MINUTES);
            session.insert(new Reading<>(0.05 * ++aux, "container", "temperature", clock.getCurrentTime()));
            session.fireAllRules();
        }
        Assert.assertTrue(container.getTemperature().getValue() == 0.05 * aux);
    }
}
