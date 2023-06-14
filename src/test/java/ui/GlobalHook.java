package ui;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.helpers.ChromiumHelper;

@Component
public class GlobalHook implements ConcurrentEventListener {
    @Autowired
    ChromiumHelper helper;

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, event -> {
            System.err.println("****** Before All JUnit5 *****");
        });
        eventPublisher.registerHandlerFor(TestRunFinished.class, event -> {
            System.err.println("****** After All JUnit5 *****");
            helper.closeApp();
        });
    }
}