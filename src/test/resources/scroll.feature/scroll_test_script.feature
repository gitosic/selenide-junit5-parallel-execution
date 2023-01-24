Feature: Scroll test

  @ScrollTestHabr
  Scenario: Scroll down - https://habr.com/ru/all
    When open 'https://habr.com/ru/all/'
    And scroll down

  @ScrollTestYandex
  Scenario: Scroll right - https://market.yandex.ru
    When open 'https://market.yandex.ru/'
    And scroll right