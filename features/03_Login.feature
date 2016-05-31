@Login
Feature: Login

  Scenario Outline: Login Success
    Given I wait for the "Welcome" screen to appear
    When I press "LoginButton"
    And I enter "<User>" into input field number 1
    And I press the enter button
    And I enter "<Password>" into input field number 2
    And I press the enter button
    And I press "LoginButton"
    Then I should see "Hello <User>"

    Examples:
      | User  | Password |
      | admin | admin    |
      | featuretest | test    |


  Scenario Outline: LogIn Failed
    Given I wait for the "Welcome" screen to appear
    When I press "LoginButton"
    And I enter "<User>" into input field number 1
    And I press the enter button
    And I enter "<Password>" into input field number 2
    And I press the enter button
    And I press "LoginButton"
    Then I should see "Login unsuccessful"

    Examples:
      | User    | Password          |
      | quatsch | ersterechtquatsch |
      | muell   | japmisttest       |



