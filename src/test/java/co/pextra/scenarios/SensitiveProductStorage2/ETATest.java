package co.pextra.scenarios.SensitiveProductStorage2;

import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import co.pextra.model2.Entity;
import co.pextra.model2.Reading;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class ETATest extends SessionTest {
    static private final Logger LOG = LoggerFactory.getLogger(ETATest.class);

    @Test
    public void eta () {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.addEventListener(new SCENESessionListener());

        LOG.info("Now running data");
        LatLng vix = new LatLng(-20.2976178, -40.2957768);
        LatLng vv = new LatLng(-20.347782, -40.294953);
        Person john = new Person("john");
        john.getLocation().setValue(new Reading<>(vix, john.getLocation().getId()));

        ProductType productType = new ProductType("Marijuana", 15.0, 5.0);
        Batch batch = new Batch("batch", productType);

        Container container = new Container("container", batch);
        container.getLocation().setValue(new Reading<>(vv, john.getLocation().getId()));

        session.insert(john);
        john.getIntrinsicContexts().forEach(session::insert);
        session.insert(container);
        container.getIntrinsicContexts().forEach(session::insert);
        session.insert(batch);
        batch.getIntrinsicContexts().forEach(session::insert);

        session.fireAllRules();
        clock.advanceTime(1, TimeUnit.HOURS);
        john.getWatchers().add(new Watch(john, batch));
        john.getWatchers().forEach(session::insert);
        session.fireAllRules();

        clock.advanceTime(1, TimeUnit.HOURS);
        for (int i = 0; i < 10; i++) {
            clock.advanceTime(1, TimeUnit.SECONDS);
            session.insert(new Reading<>(Person.walk(john, 2.5 * i,0), john.getLocation().getId(), clock.getCurrentTime()));
            session.fireAllRules();
        }
    }
}
