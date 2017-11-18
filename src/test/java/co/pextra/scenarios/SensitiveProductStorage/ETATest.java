package co.pextra.scenarios.SensitiveProductStorage;

import br.ufes.inf.lprm.scene.SceneApplication;
import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import javassist.ClassPool;
import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.core.event.DefaultRuleRuntimeEventListener;
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
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ETATest {
    static private final Logger LOG = LoggerFactory.getLogger(ETATest.class);

    @Test
    public void containerTemperature () {
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
//        session.addEventListener(new DebugAgendaEventListener());

        LOG.info("Now running data");
        ProductType marijuana = new ProductType("marijuana", 10.0, -7.0);
        Person john = new Person("John Doe", -20.2976178, 40.2957768);
        Container container = new Container(john, "container do john", -20.2976178, 40.2957768, 0);
        Batch batch = new Batch("batch do john", container, marijuana, john);
        EstimateTimeOfArrival eta = new EstimateTimeOfArrival("john ETA", john, container);
        john.getContainers().add(container);
        john.getBatches().add(batch);
        session.insert(john);
        john.getContexts().forEach(session::insert);
        session.insert(container);
        container.getContexts().forEach(session::insert);
        session.insert(batch);
        batch.getContexts().forEach(session::insert);
        session.insert(eta);
        session.fireAllRules();
        long initialTime = clock.getCurrentTime();
        int aux = 1;
        while (clock.getCurrentTime() < initialTime + TimeUnit.HOURS.toMillis(2)) {
            clock.advanceTime(30, TimeUnit.MINUTES);
            session.insert(Temperature.newReading(
                    "container do john",
                    "temperature-container do john",
                    0.05 * aux++,
                    clock
            ));
            session.fireAllRules();
        }
        for (int i = 0; i < 10; i++) {
            clock.advanceTime(1, TimeUnit.SECONDS);
            session.insert(Location.walk(john.getLocation(), 2.5 * i,0, clock));
            session.fireAllRules();
        }
    }
}
