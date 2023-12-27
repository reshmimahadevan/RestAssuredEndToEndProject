package stepDefinition;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils {

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	JsonPath js;
	static String place_id;

	TestDataBuild tdb = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string} {string} {string} {int} {double} {double}")
	public void add_place_payload_with(String name, String address, String language, String phno, String website,
			Integer accuracy, double latitude, double longitude) // io.cucumber.datatable.DataTable dataTable)
			throws IOException {

		res = given().spec(requestSpecification())
				.body(tdb.addPlacePayload(name, address, language, phno, website, accuracy, latitude, longitude));// dataTable));

	}

	@When("user calls {string} with {string} HTTP request")

	public void user_calls_with_http_request(String resource, String httpMethod) {

		// Constructor will be call with .valueOf()
		APIResources apiRes = APIResources.valueOf(resource);
		System.out.println(apiRes.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("POST"))
			response = res.when().post(apiRes.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = res.when().get(apiRes.getResource());
	}

	@Then("the API call got success with status code {int}")

	public void the_api_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")

	public void in_response_body_is(String keyValue, String expectedValue) {

		assertEquals(getJsonPath(response, keyValue), expectedValue);

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);

	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		res = given().spec(requestSpecification()).body(tdb.deletePlacePayload(place_id));

	}

}
