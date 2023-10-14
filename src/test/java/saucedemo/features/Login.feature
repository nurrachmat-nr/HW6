Feature: Log in to the web application

  @OnlyOneTime
  Scenario Outline: Standard user can login with valid cridential
    Given user open the web address
    Then user input <username> as username
    And user input <password> as password
    Then user click login button
    Then user succesfully login and entered home page (Inventory Page)

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Standard user can login with invalid cridential
    Given user open the web address
    Then user input <username> as username
    And user input <password> as password
    Then user click login button
    Then Error Message Should be displayed

    Examples:
      | username      | password |
      | standard_user | 123456   |
