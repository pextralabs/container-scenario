package co.pextra.scenarios.SensitiveProductStorage;

import br.ufes.inf.lprm.scene.SceneApplication;
import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import javassist.ClassPool;
import org.drools.core.time.SessionPseudoClock;
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

public class SpeedTest {
    static private final Logger LOG = LoggerFactory.getLogger(SpeedTest.class);

    @Test
    public void test () {
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

        Person john = new Person("John Doe", -20.2976178, 40.2957768);
        session.insert(john);
        john.getContexts().forEach(session::insert);
        clock.advanceTime(1, TimeUnit.HOURS);
        for (int i = 0; i < 10; i++) {
            clock.advanceTime(1, TimeUnit.SECONDS);
            session.insert(Person.walk(john, 2.5,0, clock));
            session.fireAllRules();
        }
        LOG.info(john.getLocation().toString());
    }
}
