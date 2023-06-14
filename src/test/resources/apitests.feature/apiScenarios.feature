@ApiTests
Feature: Simple API tests


  @TestQA1
  Scenario: 1 name of the api test
    When Get a list of users from "api/users?page=2" page
    Then users and avatars names match

  @TestQA2
  Scenario: 2 name of the api test
    When Get a list of users from "api/users?page=2" page
    Then users and avatars names match
