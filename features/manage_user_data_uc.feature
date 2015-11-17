Feature: manage personal user data
  As a user
  I want manage personal user data
  so that I update my personal data

  Scenario: Try to enter the edit tab, but you are not logged in
    Given the App is started
    And there is an internet connection
    And I am located at the profile screen
    And I am not logged in
    When I press the edit button
    Then I should be redirected to the Login Screen


  Scenario: Try to enter the edit tab and your are logged in
    Given the app is started
    And there is an internet connection
    And I am logged in
    When I press the edit button
    Then I should be redirect to the Edit Page

  Scenario: Edit user data and the changes are valid
    Given I am on the edit tab
    And I changed at least one data field
    And I entered valid values
    When I press the save button
    Then I should be redirected to my profile, where my updated data will be shown

  Scenario: Edit user data and the changes are not valid
    Given I am on the edit tab
    And I changed at least one data field
    And I entered illegal values
    When I press the save button
    Then I should be redirect to the edit tab, and I will see an error message about which data is illegal