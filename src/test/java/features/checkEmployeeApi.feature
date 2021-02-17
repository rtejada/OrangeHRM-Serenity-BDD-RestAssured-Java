Feature: Check the services of OrangeHRM employees API


  Scenario: Get a specific employee
    Given the application is running
    When I want to get a specific employee
    Then the API should return the status "200"

  Scenario Outline: Create a new employee
    Given the application is running
    When I want to create a new employee "<name>", "<middlename>", "<lastname>"
    Then the API should return the status "200"

    Examples:
    | name  | middlename      | lastname |
    | Karen | Intelligent     | Beautiful      |


  Scenario: Update a specific employee
    Given the application is running
    When I want to update data employee
    Then the API should return the status "200"

