Feature: Remove Product from the card

  Background: User is logged in and added product to the cart
    Given user in login page
    When user submit username and password
    Then user was logged in successfully
    Then user was added product to the cart

  @RemoveProductInYourCartPage
  Scenario Outline: User remove single product from cart in Your Cart Page
    Given user is in Your Cart Page
    Then user has <product_name> in the cart
    Then user click Remove button
    Then <item_left> product left in the cart
    Examples:
      | product_name            | item_left    |
      | Sauce Labs Bolt T-Shirt | 0            |

  @RemoveProductInYourCartPage
  Scenario Outline: User remove one product from cart in Your Cart Page
    Given user was added one more product to the cart
    And user is in Your Cart Page
    Then user has <product_name> in the cart
    Then user click Remove button
    Then <item_left> product left in the cart
    Examples:
      | product_name            | item_left    |
      | Sauce Labs Bolt T-Shirt | 1            |