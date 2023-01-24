package org.example.stepdefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.helpers.ChromiumHelper;
import org.springframework.test.context.ContextConfiguration;

import java.nio.file.Path;

@CucumberContextConfiguration
@ContextConfiguration(classes = ChromiumHelper.class)
public class SpringConf {

    public SpringConf() {
    }

    static {
        Path driverPath = ChromiumHelper.getDriverPath();
        System.setProperty("webdriver.chrome.driver", driverPath.toAbsolutePath().toString());
    }
}