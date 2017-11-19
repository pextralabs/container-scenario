package co.pextra.scenarios.SensitiveProductStorage2;

import br.ufes.inf.lprm.scene.SceneApplication;
import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import co.pextra.model2.Reading;
import javassist.ClassPool;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class PersonTest extends SessionTest{
    static private final Logger LOG = LoggerFactory.getLogger(PersonTest.class);

    @Test
    public void instantiation () {
        Person john = new Person("john");
        Location location = john.getLocation();
        Assert.assertNotNull(location);
        Assert.assertEquals("location", location.getId());
    }

    @Test
    public void location () {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.addEventListener(new SCENESessionListener());

        LOG.info("Now running data");

        Person john = new Person("john");
        session.insert(john);
        john.getIntrinsicContexts().forEach(session::insert);

        session.fireAllRules();

        LatLng vix = new LatLng(-20.2976178, 40.2957768);
        Assert.assertNull(john.getLocation().getValue());

        session.insert(new Reading<>(vix, "john", "location"));
        session.fireAllRules();
        Assert.assertNotNull(john.getLocation().getValue());
        Assert.assertEquals(john.getLocation().getValue(), vix);
    }

    @Test
    public void speed () {
        KieSession session = this.startSession(this.makePseudoClockConfiguration());
        SessionPseudoClock clock = session.getSessionClock();
        session.addEventListener(new SCENESessionListener());

        LOG.info("Now running data");
        Person john = new Person("john");
        session.insert(john);
        john.getIntrinsicContexts().forEach(session::insert);
        session.insert(new Reading<>(new LatLng(-20.2976178, 40.2957768), "john", "location", clock.getCurrentTime()));
        session.fireAllRules();
        clock.advanceTime(1, TimeUnit.HOURS);
        for (int i = 0; i < 10; i++) {
            clock.advanceTime(1, TimeUnit.SECONDS);
            session.insert(new Reading<>(Person.walk(john, 2.5 * i,0), "john", "location", clock.getCurrentTime()));
            session.fireAllRules();
        }
    }
}
