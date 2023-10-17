Feature: Logout from web application

  Background: User is Logged in
    Given user in login page
    When user submit username and password
    Then user was logged in successfully

  @LogoutPositive
  Scenario: User logout from the page
    Given user was in home page
    Then user click burger icon
    Then user click Logout menu
    Then user successfully logout and redirected to the login page


  @LogoutNegative
  Scenario: User logout and try to get back to home page
    Given user was logged out successfully
    Then user navigate back
    Then error message should be displayed
