Feature: Add Product in the cart

  Background: User is logged in and in the home page
    Given user in login page
    When user submit username and password
    Then user was logged in successfully
    And user was in home page

  @AddOneProductToCart
  Scenario Outline: User add 1 product to cart
    Given user is in Products page
    Then user select product with id <product_id>
    Then user add <product_name> to the cart by click Add to cart button
    Then <item_count> product added to cart
    Then user click Shopping Cart icon on the top right of the page
    Examples:
    | product_id  | product_name            | item_count   |
    | 1           | Sauce Labs Bolt T-Shirt | 1            |

  @ContinueShopping
  Scenario Outline: User add 1 more product to cart
    Given user was added product to the cart
    And user is in Your Cart Page
    Then user click Continue Shopping button
    Then user is in Products page
    Then user select product with id <product_id>
    Then user add <product_name> to the cart by click Add to cart button
    Then <item_count> product added to cart
    Examples:
      | product_id  | product_name            | item_count  |
      | 4           | Sauce Labs Backpack     | 2           |
      | 2           | Sauce Labs Onesie       | 2           |