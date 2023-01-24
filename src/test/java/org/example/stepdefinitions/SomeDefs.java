package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import org.example.helpers.ChromiumHelper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.example.pages.TestPages.footer;
import static org.example.pages.TestPages.thingsOnPage;

public class SomeDefs {

    @Autowired
    public ChromiumHelper helper;

    // Vertical scroll on the page. We go down to the footer.
    @And("scroll down")
    public void scrollWithKinopoisk() {
        footer().scrollIntoView("{block: \"end\"}");
    }

    // Horizontal scroll with products on the page
    @And("scroll right")
    public void scrollWithYandexMarket() {
        thingsOnPage().scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}");
    }

}
