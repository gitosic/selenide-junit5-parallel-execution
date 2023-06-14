package ui;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.suite.api.*;
import org.springframework.test.context.ContextConfiguration;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResources(value = {
        @SelectClasspathResource("sometests.feature"),
        @SelectClasspathResource("scroll.feature")
})
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ui.stepdefinitions")
@ConfigurationParameter(key = JUNIT_PLATFORM_NAMING_STRATEGY_PROPERTY_NAME, value = "long")
@ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "true")
@ConfigurationParameter(key = "cucumber.execution.parallel.mode.default", value = "same_thread")
@ConfigurationParameter(key = "cucumber.execution.parallel.mode.classes.default", value = "same_thread")
@ConfigurationParameter(key = "cucumber.execution.parallel.config.strategy", value = "custom")
@ConfigurationParameter(key = "cucumber.execution.parallel.config.custom.parallelism", value = "2")
@ConfigurationParameter(key = "cucumber.execution.parallel.config.custom.class",
        value = "ui.CustomStrategy")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:src/test/resources/report/Cucumber.html," +
                " json:src/test/resources/report/Cucumber.json," +
                " junit:src/test/resources/report/Cucumber.xml")
@CucumberContextConfiguration
@ContextConfiguration
public class RunnerTest {

    @BeforeAll
    public static void setupWebdriver() {
    }

    @BeforeEach
    void beforeEach() {
        System.err.println("Before Each Cucumber");
        Assertions.assertEquals(1, 1);
    }

    @AfterEach
    void afterEach() {
        System.err.println("After Each Cucumber");
        Assertions.assertEquals(1, 1);
    }

}