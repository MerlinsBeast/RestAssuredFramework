Feature: Validating the Place API'S

  @AddPlace @SmokeTest
  Scenario Outline: Validating AddPlaceAPI is able to add the Place successfully
    Given Add Place Payload with "<name>" "<address>" "<language>" "<website>" and "<Phoneno>"
    When User calls "ADDPlaceAPI" with "POST" method
    Then Validating API successfully return "status-code" as 200
    And Response body returns "status" as "OK"
    And Response body returns "scope" as "APP"
    And Check "GETPlaceAPI" is hit with placeID queryparameter
    When User calls "GETPlaceAPI" with "GET" method
    And Check "GETPlaceAPI" return the response with "<name>" provided
    Examples:
      |name   |address | language| website| Phoneno|
      |Vijay Yadav   |Prasan Niwas | Hindi| https://wwww.getAutomationGrip.com| 8689946239|
      |Ajay Yadav   |Runwal Gardens| English| https://wwww.automationMadeEasy.com| 8689946238|

  @DeletePlace @SmokeTest
   Scenario: Validating the DeletePlaceAPI is able to delete the existing data
     Given User has access to Delete Place Payload
     When User calls "DELETEPlaceAPI" with "POST" method
     Then Validating API successfully return "status-code" as 200
     And Response body returns "status" as "OK"