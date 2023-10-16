Feature: Product checkout

  Scenario: User provide full information when checkout product
    Given user is in Products page
    Then user select the product want to buy.
    Then user click Add to cart button
    Then user click Shopping Cart icon on the top right of the page
    Then click Checkout page
    Then user input a valid first name
    And user input a valid last name
    And user input a valid zip or postal code
    Then user click Continue page
    Then user click Finish button
    Then user receives confirmation messages

