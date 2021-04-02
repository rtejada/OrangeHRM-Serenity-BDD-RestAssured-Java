Feature: Contact details of the employees of OrangeHRM


  Scenario Outline: Add details contact to employees
    Given the application is running
    When I add contact detail to employee "<idEmployee>", "<addressStreet>", "<city>", "<country>", "<zip>", "<homeTelephone>", "<mobile>"
    Then the response status should be "200"
    And the response body should be "<success>"

    Examples:

      | idEmployee | addressStreet | city   | country | zip | homeTelephone | mobile | success            |
      | 209        | street        | Madrid | Spain   | 0   | 9             | 6      | Successfully Saved |


