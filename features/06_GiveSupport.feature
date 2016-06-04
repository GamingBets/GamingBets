@support
Feature: Give Support

  Scenario: Give Support
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "admin" into input field number 1
    And I enter "admin" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello admin" to appear
    And I press the menu key
    And I press "Answer a ticket"
    And I press list item number 1
    And I enter "Solution 1" into input field number 1
    And I press "Send"
    Then I should see "Answer sent"