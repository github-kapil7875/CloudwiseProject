Feature: Cloudwisers duplicate name check

  Scenario: Test Case 1- Open cloudwise URL and change the language
    Given launch "chrome" browser
    When open webpage URL "https://cloudwise.nl"
    And select language as "English"
    Then language should be changed as ".eu" in url

  Scenario: Test Case 2- Navigate to All cloudwisers page and check duplicates name
    Given go to "This is Cloudwise" drop down 
    When click on "All cloudwisers" sub menu
    Then shoud be navigated to "All cloudwisers" page
    And check duplicate name