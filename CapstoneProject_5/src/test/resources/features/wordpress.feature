Feature: WordPress Website Testing

  Scenario: Verify Download and Get WordPress
    Given User launches WordPress website
    Then Verify the page title
    When User clicks on Download and Get WordPress
    Then Verify middle text as "Get WordPress"

  Scenario: Verify Photo Directory Search
    Given User launches WordPress website
    When User clicks Community and Photo Directory
    When User searches for "nature"
    Then Verify pictures are displayed