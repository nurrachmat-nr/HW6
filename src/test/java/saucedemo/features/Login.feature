Feature: Log in to the web application

  @LoginPositive
  Scenario Outline: User can login with valid credential
    Given user open the web address
    Then user input <username> as username
    And user input <password> as password
    Then user click login button
    Then user successfully login and entered home page (Inventory Page)

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @LoginNegative
  Scenario Outline: User can't login with wrong username or password
    Given user open the web address
    Then user input <username> as username
    And user input <password> as password
    Then user click login button
    Then error message should be displayed

    Examples:
      | username      | password     |
      | standard_user | 123456       |
      | wrong_user    | secret_sauce |

  @LoginNegative2
  Scenario Outline: User can't login with locked username
    Given user open the web address
    Then user input <username> as username
    And user input <password> as password
    Then user click login button
    Then error message should be displayed

    Examples:
      | username        | password        |
      | locked_out_user | secret_sauce    |
