Feature: Check the services of OrangeHRM employees API


  Scenario Outline: Create a new employee
    Given the application is running
    When I want to create a new employee "<name>", "<middlename>", "<lastname>"
    Then the API should return the status "200"

    Examples:
      | name | middlename | lastname |
      | Ana  | Bouer      | Yast     |


  Scenario Outline: Update a specific employee
    Given the application is running
    When I want to update data employee "<otherId>", "<status>", "<nationality>"
    Then the API should return the status "200"

    Examples:
      | otherId | status | nationality |
      | ID      | Activo | Spanish     |


  Scenario: Get a specific employee
    Given the application is running
    When I want to get a specific employee
    Then the API should return the status "200"
    And the employees data has been modified

