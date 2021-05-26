Feature: Test Colour Lovers API

  Scenario: Make sure that numviews is greater than 4000
    Given we made Get request to colourlovers api "http://www.colourlovers.com/api/patterns"
    Then Response code should be 200 
    And numViews from response to be greater than 4000
