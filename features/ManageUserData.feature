@manageuserdata
Feature: Manage User Data

  @this
  Scenario: Manage Data
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "admin" into input field number 1
    And I enter "admin" into input field number 2
    And I press "LoginButton"
    And I wait for "Login successful" to appear
    And I press the menu key
    And I press "Manage user data"
    And I press "reg1"
    And I wait for "You Selectedreg1"
    And I press "Confirm Changes"
    Then I should see "User edited"

