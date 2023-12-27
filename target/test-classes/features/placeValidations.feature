Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place being successfully added using AddPlaceAPI
   Given Add Place Payload with "<name>" "<address>" "<language>" "<phone_number>" "<website>" <accuracy> <latitude> <longitude>
    #|shoe park | shop  |
   When user calls "addPlaceAPI" with "POST" HTTP request
   Then the API call got success with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   And verify place_Id created maps to "<name>" using "getPlaceAPI"	
 
Examples:
    |  name          | address                 | language |phone_number        |         website                | accuracy |latitude    |longitude |
    |  RHouse        |TimeSquare               | English  |(+91) 967 888 3123  | https://rahulshettyacademy.com |   45     |-38.383494  |33.427362 |
   #|  EHouse        |Timesquare               | French   |(+91) 981 978 3728  | https://rahulshettyacademy.com |   50     |-18.539410  |23.912323 |
    

@DeletePlace  @Regression  
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" HTTP request
	Then the API call got success with status code 200
	And "status" in response body is "OK"    