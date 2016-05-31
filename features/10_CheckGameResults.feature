@chkGameResults
Feature: Check Game Results

Scenario: Check Game Results
  Given I wait for the "Welcome" screen to appear
  When I press "Login"
  And I enter "featuretest" into input field number 1
  And I enter "test" into input field number 2
  And I press "LoginButton"
  And I wait for "Hello featuretest" to appear
  And I wait for "Here are the most recent results" to appear