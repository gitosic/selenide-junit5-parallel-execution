package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.helpers.ChromiumHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@ContextConfiguration(classes = ChromiumHelper.class)
public class MyStepdefs {

    @Autowired
    public ChromiumHelper helper;

    @When("open {string}")
    public void open(String page) {
        helper.openUrl(page);
    }

    @When("open app ui - Yandex")
    public void openYandex() {
        helper.openYandex();
        $("title").shouldHave(attribute("text", "Яндекс"));
//        $x("").shouldBe(visible);
        System.out.println("Открывается - Яндекс");
    }

    @When("open app ui - Google")
    public void openGoogle() {
        helper.openUrl("https://www.google.com/");
        $("title").shouldHave(attribute("text", "Google"));
        System.out.println("Открывается - Google");
    }

    @And("wait {int} milliseconds")
    public void waitMilliseconds(int time) {
        sleep(time);
    }

    @Then("you will see {string}")
    public void youWillSee(String text) {
        System.out.println("You will see " + text);
    }

}

