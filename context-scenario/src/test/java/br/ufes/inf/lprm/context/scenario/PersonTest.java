package br.ufes.inf.lprm.context.scenario;

import br.ufes.inf.lprm.context.model.ContextValue;
import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class PersonTest extends SessionTest{
    static private final Logger LOG = LoggerFactory.getLogger(PersonTest.class);

    @Test
    public void instantiation () {
        Person john = new Person(("john"));
        Location location = john.getLocation();
        Assert.assertNotNull(location);
        Assert.assertNull(location.getContextValue());
        Assert.assertEquals("john-location", location.getCid());
    }

    @Test
    public void location () {
        try {
            KieSession session = this.startSession(this.makePseudoClockConfiguration());
            SessionPseudoClock clock = session.getSessionClock();
            session.addEventListener(new SCENESessionListener());
            session.setGlobal("clock", clock);

            LOG.info("Now running data");

            Person john = new Person(("john"));
            session.insert(john);
            john.getContexts().forEach(session::insert);

            session.fireAllRules();

            LatLng vix = new LatLng(-20.2976178, 40.2957768);
            Assert.assertNull(john.getLocation().getContextValue());

            session.insert(new ContextValue<>(vix, ("john-location"), clock.getCurrentTime()));
            session.fireAllRules();
            Assert.assertNotNull(john.getLocation().getContextValue());
            Assert.assertEquals(john.getLocation().getValue(), vix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void speed () {
        try {
            KieSession session = this.startSession(this.makePseudoClockConfiguration());
            SessionPseudoClock clock = session.getSessionClock();
            session.addEventListener(new SCENESessionListener());
            session.setGlobal("clock", clock);

            LOG.info("Now running data");
            Person john = new Person(("john"));
            session.insert(john);
            john.getContexts().forEach(session::insert);
            session.insert(new ContextValue<>(new LatLng(-20.2976178, 40.2957768), ("john-location"), clock.getCurrentTime()));
            session.fireAllRules();
            clock.advanceTime(1, TimeUnit.HOURS);
            for (int i = 0; i < 10; i++) {
                clock.advanceTime(1, TimeUnit.SECONDS);
                session.insert(new ContextValue<>(Person.walk(john, 2.5 * i,0), ("john-location"), clock.getCurrentTime()));
                session.fireAllRules();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
