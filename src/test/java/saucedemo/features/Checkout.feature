Feature: Product checkout

  Background: User is logged in and in the home page
    Given user in login page
    When user submit username and password
    Then user was logged in successfully
    And user was in home page

  @CheckoutPositive
  Scenario: User provide full information when checkout product
    Given user was added product to the cart
    And user is in Your Cart Page
    Then user click Checkout page
    Then user input a valid first name
    And user input a valid last name
    And user input a valid zip or postal code
    Then user click Continue page
    Then user click Finish button
    Then user receives confirmation messages

  @CheckoutNegative
  Scenario: User not provide full information when checkout product
    Given user was added product to the cart
    And user is in Your Cart Page
    Then user click Checkout page
    Then user click Continue page
    Then error message should be displayed


  @CheckoutPositifBug
  Scenario: User not allowed to check out without product in the shopping cart
    Given user is in Your Cart Page
    Then user click Checkout page
    Then user forbidden to checkout without product

