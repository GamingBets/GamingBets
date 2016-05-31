@placeWagerBet
Feature: Place a Wager Bet

  Scenario: Place Wager Bet
    Given I wait for the "Welcome" screen to appear
    When I press "Login"
    And I enter "featuretest" into input field number 1
    And I enter "test" into input field number 2
    And I press "LoginButton"
    And I wait for "Hello featuretest" to appear
    And I press the menu key
    And I press "Place a bet"
    And I press list item number 1
    And I press list item number 1
    And I press "flag_wager_bet"
    And I press "btn_PlaceBet"
    Then I should see "Wager Bet placed!"

