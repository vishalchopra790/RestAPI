Feature: Validating Place API's
  @AddPlace @Regression
  Scenario Outline: Verify if place is being added using ADD Api place
    Given Add Place Payload with "<name>" , "<address>", "<language>"
    When user calls "AddPlaceAPI" with "Post" Http request
    Then The API call got success with status code 200
    And "status" in response is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps  to "<name>" using "GetPlaceAPI"

    Examples:
     |name        |address                 |language     |
     |vishal      |  Ramdutt enclave       | English     |
     #|sahil       | uttam nagar            |hindi        |
  @DeletePlace @Regression
  Scenario: Verify Delete Place function is worjing or not
    Given Delete Place Pay load
    When user calls "DeletePlaceAPI" with "Post" Http request
    Then The API call got success with status code 200
    And "status" in response is "OK"
