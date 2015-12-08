Feature: aaa

  Scenario Outline: LogIn
    Given I wait for the "Welcome" screen to appear
    When I press "button"
    And I enter "<User>" into input field number 1
    And I press the enter button
    And I enter "<Password>" into input field number 2
    And I press the enter button
    And I press "LoginButton"
    Then I should see "Login successful"

    Examples:
      | User  | Password |
      | Felix | pass     |
      | Test  | test     |


  Scenario Outline: LogIn Failed
    Given I wait for the "Welcome" screen to appear
    When I press "button"
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


