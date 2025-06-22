@product
Feature: Register

  @newuser-token
  Scenario: Successfully register and receive account token
    Given the user has email "eve.holt@reqres.in" and password "pistol"
    When the user sends a POST request to "/api/register"
    Then the response status code should be 200
    And the response JSON should contain a non-null "token"
    And the response JSON should contain a non-null "id"