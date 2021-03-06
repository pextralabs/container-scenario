package br.ufes.inf.lprm.context.scenario;

import br.ufes.inf.lprm.context.model.ContextValue;
import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.TimeUnit;


public class ETATest extends SessionTest {
    static private final Logger LOG = LoggerFactory.getLogger(ETATest.class);

    @Test
    public void eta () throws Exception {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.addEventListener(new SCENESessionListener());
        session.setGlobal("clock", clock);

        LOG.info("Now running data");
        LatLng vix = new LatLng(-20.2976178, -40.2957768);
        LatLng vv = new LatLng(-20.347782, -40.294953);
        Person john = new Person(("john"));
        Location johnsLocation = john.getLocation();
        johnsLocation.setContextValue(johnsLocation.createContextValue(vix));

        ProductType productType = new ProductType("Marijuana", 15.0, 5.0);
        Batch batch = new Batch(("batch"), productType);

        Container container = new Container(("container"), batch);
        Location containersLocation = container.getLocation();
        containersLocation.setContextValue(containersLocation.createContextValue(vv));

        session.insert(john);
        john.getContexts().forEach(session::insert);
        session.insert(container);
        container.getContexts().forEach(session::insert);
        session.insert(batch);
        batch.getContexts().forEach(session::insert);

        session.fireAllRules();
        clock.advanceTime(1, TimeUnit.HOURS);
        FactHandle watchFact = session.insert(new Watch(("container"), john, batch));
//        session.insert(new RelationalContextInsert(Watch.class, "watch-john-batch", Arrays.asList("john", "batch"), clock.getCurrentTime()));
        session.fireAllRules();
        clock.advanceTime(1, TimeUnit.HOURS);
        for (int i = 0; i < 10; i++) {
            clock.advanceTime(1, TimeUnit.SECONDS);
            session.insert(new ContextValue<>(Person.walk(john, 2.5 * i,0), john.getLocation().getCid(), clock.getCurrentTime()));
            session.fireAllRules();
        }
        clock.advanceTime(1, TimeUnit.HOURS);
        session.delete(watchFact);
//        session.insert(new RelationalContextDelete("watch-john-batch", clock.getCurrentTime()));
        session.fireAllRules();
    }

    @Test
    public void etaBiggerThanTTT () throws Exception {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.setGlobal("clock", clock);
        session.addEventListener(new SCENESessionListener());

        LOG.info("Now running data");
        ProductType productType = new ProductType("marijuana", 10.0, 0.0);
        Batch batch = new Batch(("batch"), productType);
        LatLng vix = new LatLng(-20.2976178, -40.2957768);
        LatLng vv = new LatLng(-20.347782, -40.294953);
        Container container = new Container(("freezer1@office"), batch);
        Location containersLocation = container.getLocation();
        Temperature containersTemperature = container.getTemperature();
        containersLocation.setContextValue(containersLocation.createContextValue(vix));
        containersTemperature.setContextValue(containersTemperature.createContextValue(0.0));

        Person john = new Person(("john"));
        Location johnsLocation = john.getLocation();
        johnsLocation.setContextValue(johnsLocation.createContextValue(vv));
        session.insert(batch);
        batch.getContexts().forEach(session::insert);
        session.insert(john);
        john.getContexts().forEach(session::insert);
        session.insert(container);
        container.getContexts().forEach(session::insert);
        FactHandle watchFact = session.insert(new Watch(("watch-john-batch"), john, batch));
        session.fireAllRules();
        double initialTime = clock.getCurrentTime();
        double value = 0;
        while (clock.getCurrentTime() < initialTime + TimeUnit.HOURS.toMillis(1)) {
            clock.advanceTime(30, TimeUnit.SECONDS);
            value += 0.01;
            session.insert(new ContextValue<>(value, container.getTemperature().getCid(), clock.getCurrentTime()));
            session.fireAllRules();
        }
        initialTime = clock.getCurrentTime();
        while (clock.getCurrentTime() < initialTime + TimeUnit.HOURS.toMillis(1)) {
            clock.advanceTime(30, TimeUnit.SECONDS);
            value -= 0.01;
            session.insert(new ContextValue<>(value, container.getTemperature().getCid(), clock.getCurrentTime()));
            session.fireAllRules();
        }
        session.delete(watchFact);
    }
}
