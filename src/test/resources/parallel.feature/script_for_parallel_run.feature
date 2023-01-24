#running tests in parallel
@UiTests
Feature: Checking multiple windows

  @TestQA1
  Scenario: Open yandex - case 1
    When open app ui - Yandex
    And wait 100 milliseconds
    Then you will see "Yandex 1"
    And wait 100 milliseconds

  @TestQA2
  Scenario: Open google - case 2
    When open app ui - Google
    And wait 100 milliseconds
    Then you will see "Google 2"
    And wait 100 milliseconds

  @TestQA3
  Scenario: Open yandex - case 3
    When open app ui - Yandex
    And wait 100 milliseconds
    Then you will see "Yandex 3"
    And wait 100 milliseconds

  @TestQA4
  Scenario: Open google - case 4
    When open app ui - Google
    And wait 100 milliseconds
    Then you will see "Google 4"
    And wait 100 milliseconds