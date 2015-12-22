@feedback
Feature: Give Feedback

  @feedbackguest
  Scenario Outline: Feedback as Guest Success
    Given I wait for the "Welcome" screen to appear
    When I press "unregLogin"
    And I press the menu key
    And I press "Write a ticket"
    And I enter "<Ticket>" into input field number 1
    And I press the enter button
    And I enter "<Mail>" into input field number 2
    And I press the enter button
    And I press "button2"
    Then I should see "Ticket sent"

    Examples:
      | Ticket     | Mail     |
      | Problem 1  | reg1     |
      | Problem 2  | reg2     |

  @feedbackuser
  Scenario Outline: Feedback as Logged In User Success
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "Felix" into input field number 1
    And I enter "pass" into input field number 2
    And I press "LoginButton"
    And I wait for "Felix" to appear
    And I press the menu key
    And I press "Write a ticket"
    And I enter "<Ticket>" into input field number 1
    And I press the enter button
    And I press "button2"
    Then I should see "Ticket sent"
    Examples:
      | Ticket |
      | Test   |



