package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TestPages {

    // page_url = https://habr.com/ru/all/
    public static SelenideElement footer() {
        return $x("//div[@class='tm-footer-menu']");
    }

    // page_url = https://market.yandex.ru
    public static SelenideElement thingsOnPage() {
        return $x("//div//span[text()='Одеяло buyson BuyRelax всесезонное, 140 х 205 см, белый']");
    }

}