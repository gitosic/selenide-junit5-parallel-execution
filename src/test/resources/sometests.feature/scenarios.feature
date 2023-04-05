@UiTests
Feature: Demo BDD


  @TestQA1
  Scenario: Habr habr
    When open 'https://habr.com/ru/all/'
    And click on the link "Разработка"
    And click on the link "Администрирование"
    Then header contains text 'КАК СТАТЬ АВТОРОМ'

  @TestQA2
  Scenario: Habr habr
    When open 'https://habr.com/ru/all/'
    And click on the link "Дизайн"
    And click on the link "Менеджмент"
    Then header contains text 'КАК СТАТЬ АВТОРОМ'

  @TestQA2
  Scenario: Habr habr
    When open 'https://habr.com/ru/all/'
    And click on the link "Маркетинг"
    Then header contains text 'КАК СТАТЬ АВТОРОМ'
