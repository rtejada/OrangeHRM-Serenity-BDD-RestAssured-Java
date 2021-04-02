Feature: Organization information of OrangeHRM


  Scenario Outline: Get all the organization's information
    Given the application is running
    When I get all organization information
    Then the response status should be  "200"
    And the answer obtained should be these data "<name>", "<taxId>", "<email>", "<province>", "<note>"

    Examples:

      | name          | taxId    | email                     | province    | note                                                                                                 |
      | QA-VSGONJyTsn | ea462462 | ea462462@ba9401b05885.com | Westminster | iULSCRiuyD5BE9GjQVNz9AYd46SUxFGE8QGCZAY80hOZMRUEPoxZPGRvJFPTZpbJXjIMbtUvd9S4XYO6O8TTOXiP1PQpILaGI9yB |



