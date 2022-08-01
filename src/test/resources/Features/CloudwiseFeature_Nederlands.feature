Feature: Cloudwisers duplicate name check

  Scenario: Test Case 1- Open cloudwise URL and change the language
    Given launch "chrome" browser
    When open webpage URL "https://cloudwise.nl"
   
  Scenario: Test Case 2- Navigate to All cloudwisers page and check duplicates name
    Given go to "Dit is Cloudwise" drop down 
    When click on "Alle Cloudwisers" sub menu
    Then shoud be navigated to "Alle Cloudwisers" page
    And check duplicate name
    
  