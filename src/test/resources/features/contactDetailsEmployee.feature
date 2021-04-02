Feature: Contact details of the employees of OrangeHRM


  Scenario Outline: Add details contact to employees
    Given the application is running
    And I want to create a new employee
    When I add contact detail to employee "<addressStreet>", "<city>", "<country>", "<zip>", "<homeTelephone>", "<mobile>", "<workEmail>", "<otherEmail>"
    Then the response status should be "200"
    And the response body should be "<success>"

    Examples:

      | addressStreet | city   | country | zip | homeTelephone | mobile | success            | workEmail     | otherEmail |
      | street        | Madrid | Spain   | 0   | 9             | 6      | Successfully Saved | @paradigma.es | @other.es  |


