Feature: Check the services of OrangeHRM employees API

  Scenario Outline: Create a new employee
    Given the application is running
    When I want to create a new employee "<name>", "<middlename>", "<lastname>"
    Then the API should return the status "200"

    Examples:
      | name | middlename | lastname |
      | Ana  | Bouer      | Yast     |


  Scenario Outline: Update data employee
    Given the application is running
    And I create a new employee
    When I want to update data employee "<name>", "<otherId>", "<status>", "<nationality>"
    Then the employees data has been modified
    And the API should return the status "200"

    Examples:
      | name | otherId | status | nationality |
      | Pia  | ID      | Activo | Spanish     |




