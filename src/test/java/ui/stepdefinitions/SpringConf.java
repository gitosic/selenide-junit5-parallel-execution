package ui.stepdefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import ui.helpers.ChromiumHelper;

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