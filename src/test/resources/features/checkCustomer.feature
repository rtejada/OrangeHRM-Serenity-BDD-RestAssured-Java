Feature: management of clients
  as a user with permissions
  I want to create and delete customers

  Scenario: create a new customer
    Given the application is running
    When I want to create a new customer
    Then confirm what the status is "200"
    And confirm that the data have been inserted

  Scenario: update data customer
    Given the application is running
    When I want to update data customer
    Then confirm what the status is "200"
    And confirm that the data have been updated

  Scenario: delete data customer
    Given the application is running
    When I want to delete data customer
    Then confirm what the status is "200"
    And confirm that the data have been deleted


