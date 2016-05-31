@chkLB
Feature: Check Leaderboard

  @chkLBGuest
  Scenario: Check Leaderboard as Guest Success
    Given I wait for the "Welcome" screen to appear
    When I press "LoginButton"
    And I enter "featuretest" into input field number 1
    And I press the enter button
    And I enter "test" into input field number 2
    And I press the enter button
    And I press "LoginButton"
    Then I should see "Hello featuretest"
    And I press the menu key
    And I press "Leaderboard"
    Then I should see "debug"





