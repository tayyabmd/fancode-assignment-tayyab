Feature: Verify Completed Task Percentage for Users in FanCode City

  Scenario: Users in FanCode City should have more than 50% completed tasks
    Given User has the todo tasks
    And User belongs to the city FanCode
    Then User Completed task percentage should be greater than 50%
