package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.helpers.ChromiumHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.example.pages.Elements.headerLinks;
import static org.example.pages.Elements.headerSite;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @And("click on the link {string}")
    public void clickOnTheLink(String text) {
        headerLinks(text).click();
    }

    @Then("header contains text {string}")
    public void headerContainsText(String text) {
        String textFromHeader = headerSite().getText();

        //Var. 1
        assertTrue(textFromHeader.equals(text));
        //Var. 2
        headerSite().shouldHave(text("КАК СТАТЬ АВТОРОМ"));
//        headerSite().shouldHave(text("КАК СТАТЬ АВТОРОМ для скриншота с ошибкой"));
    }
}

