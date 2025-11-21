@tag
Feature: Error validation

  I want to use this template for my feature file

  Background:
    Given I landed on Ecommerce Page 

  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    And Checkout <productName> and submit the order
    Then "Epic sadface: Username and password do not match any user in this service" message is displayed 

    Examples:
      | name          | password     | productName         |
      | standard_user | secret_sauce | Sauce Labs Backpack |
