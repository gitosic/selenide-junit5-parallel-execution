package ui.stepdefinitions;

import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import ui.helpers.ChromiumHelper;
import ui.pages.TestPages;

public class SomeDefs {

    @Autowired
    public ChromiumHelper helper;

    // Vertical scroll on the page. We go down to the footer.
    @And("scroll down")
    public void scrollWithKinopoisk() {
        TestPages.footer().scrollIntoView("{block: \"end\"}");
    }

    // Horizontal scroll with products on the page
    @And("scroll right")
    public void scrollWithYandexMarket() {
        TestPages.thingsOnPage().scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}");
    }

}
