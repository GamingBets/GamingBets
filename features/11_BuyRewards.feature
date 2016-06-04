@buyR
Feature: Buy / Get Rewards

  Scenario: Buy a Profile Pic
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "featuretest" into input field number 1
    And I enter "test" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello featuretest" to appear
    And I press the menu key
    And I press "Unlock Profile Images"
    And I press list item number 1
    And I press "Sure, you dumb ass!"
    Then I should see "UserModel edited"

