@chkLB
Feature: Check Leaderboard

  @chkLBGuest
  Scenario: Check Leaderboard as Guest Success
    Given I wait for the "Welcome" screen to appear
    When I press "unregLogin"
    And I press the menu key
    And I press "Leaderboard"
    Then I should see "Felix"





