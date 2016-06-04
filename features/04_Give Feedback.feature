@feedback
Feature: Give Feedback

  @feedbackuser
  Scenario Outline: Feedback as Logged In User Success
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "featuretest" into input field number 1
    And I enter "test" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello featuretest" to appear
    And I press the menu key
    And I press "Write a ticket"
    And I enter "<Ticket>" into input field number 1
    And I press the enter button
    And I press "button2"
    Then I should see "Ticket sent"
    Examples:
      | Ticket |
      | Test   |



