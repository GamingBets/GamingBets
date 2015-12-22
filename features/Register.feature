Feature: Register

  Scenario Outline: Register Success
    Given I wait for the "Welcome" screen to appear
    When I press "RegisterButton"
    And I enter "<User>" into input field number 1
    And I press the enter button
    And I enter "<Mail>" into input field number 2
    And I press the enter button
    And I enter "<Password>" into input field number 3
    And I press the enter button
    And I enter "<Password>" into input field number 4
    And I press the enter button
    And I press "RegisterButton"
    Then I should see "Registered"

    Examples:
      | User  | Password | Mail|
      | reg1  | reg1     | reg1@reg.de |
      | reg2  | reg2     | reg2@reg.de |


  Scenario Outline: Register Failed Password dont match
    Given I wait for the "Welcome" screen to appear
    When I press "RegisterButton"
    And I enter "<User>" into input field number 1
    And I press the enter button
    And I enter "<Mail>" into input field number 2
    And I press the enter button
    And I enter "<Password>" into input field number 3
    And I press the enter button
    And I enter "NichtDasPW" into input field number 4
    And I press the enter button
    And I press "RegisterButton"
    Then I should see "Passwords don´t match!"

    Examples:
      | User  | Password | Mail|
      | reg1  | reg1     | reg1@reg.de |
      | reg2  | reg2     | reg2@reg.de |



