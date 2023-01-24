package org.example;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import org.example.helpers.ChromiumHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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