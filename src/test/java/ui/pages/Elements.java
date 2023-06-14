package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Elements {

    public static SelenideElement headerLinks(String nameOfTheLink) {
        return $x("//a[contains(@class,'tm-main-menu__item')][contains(text(),'" + nameOfTheLink + "')]");
    }
    public static SelenideElement headerSite() {
        return $x("//a[contains(@class,'tm-header__become-author-btn')]");
    }

}
