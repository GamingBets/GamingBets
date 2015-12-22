@edituserdata
Feature: Edit User Data

  Scenario: Edit Data
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "Felix" into input field number 1
    And I enter "pass" into input field number 2
    And I press "LoginButton"
    And I wait for "Login successful" to appear
    And I press the menu key
    And I press "Edit your data"
    And I press "Confirm Changes"
    Then I should see "User edited"




  Scenario: Edit PW
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "Felix" into input field number 1
    And I enter "pass" into input field number 2
    And I press "LoginButton"
    And I wait for "Login successful" to appear
    And I press the menu key
    And I press "Edit your data"
    And I enter "pass" into input field number 5
    And I enter "pass" into input field number 6
    And I go back
    And I press button number 2
    Then I should see "User edited"

  Scenario: Edit PW Fail
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "Felix" into input field number 1
    And I enter "pass" into input field number 2
    And I press "LoginButton"
    And I wait for "Login successful" to appear
    And I press the menu key
    And I press "Edit your data"
    And I enter "nein" into input field number 5
    And I enter "doch" into input field number 6
    And I go back
    And I press button number 2
    Then I should see "Passwords don´t match!"