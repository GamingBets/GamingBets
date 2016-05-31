@edituserdata
Feature: Edit User Data

  Scenario: Edit Data
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "featuretest" into input field number 1
    And I enter "test" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello" to appear
    And I press the menu key
    And I press "Edit your data"
    And I press "btn_confirmChanges"
    Then I should see "UserModel edited"


  Scenario: Edit PW
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "featuretest" into input field number 1
    And I enter "test" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello" to appear
    And I press the menu key
    And I press "Edit your data"
    And I enter "test" into input field number 5
    And I enter "test" into input field number 6
    And I press "btn_confirmPW"
    Then I should see "UserModel edited"

  Scenario: Edit PW Fail
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "featuretest" into input field number 1
    And I enter "test" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello" to appear
    And I press the menu key
    And I press "Edit your data"
    And I enter "nein" into input field number 5
    And I enter "doch" into input field number 6
    And I press "btn_confirmPW"
    Then I should see "Passwords don´t match!"