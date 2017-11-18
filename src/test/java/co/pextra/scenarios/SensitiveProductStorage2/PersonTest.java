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

public class PersonTest {
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
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        Results verifyResults = kContainer.verify();
        for (Message m : verifyResults.getMessages()) LOG.info("{}", m);
        LOG.info("Creating kieBase");
        KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
        config.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(config);
        KieSessionConfiguration pseudoConfig = KieServices.Factory.get().newKieSessionConfiguration();
        pseudoConfig.setOption(ClockTypeOption.get("pseudo"));
        LOG.info("There should be rules: ");
        for ( KiePackage kp : kieBase.getKiePackages() ) {
            for (Rule rule : kp.getRules()) LOG.info("kp " + kp + " rule " + rule.getName());
        }
        LOG.info("Creating kieSession");
        KieSession session = kieBase.newKieSession(pseudoConfig, null);
        SessionPseudoClock clock = session.getSessionClock();
        new SceneApplication(ClassPool.getDefault(), session, "sensitive-product-storage-scenario");
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
        Assert.assertEquals(john.getLocation().getValue().getValue(), vix);
    }
}
