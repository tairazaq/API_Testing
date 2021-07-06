Feature: Bulk lookup postcodes

  //Task 1.2 and 1.3
  Scenario: Bulk_Lookup_api_01_Verify that bulk lookup postcode can be retrieved
    Given that the api is available and running  with Post Resource
    When i make a post request to the bulk lookup api post request
    Then the bulk postcodes should be retrieved successfully with status code 200


  Scenario: Bulk_Lookup_api_02_Verify that bulk lookup invalid postcode can not be retrieved
    Given that the api is available and running  with invalid postcodes
    When i make a post request to the bulk lookup api post request
    Then the bulk lookup postcodes should not be retrieved with status code 200