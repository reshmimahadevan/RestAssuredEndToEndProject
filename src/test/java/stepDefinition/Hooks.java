package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		// execute this code only when place id is null
		// write a code that will give you place id

		stepDefinition m = new stepDefinition();
		//place_id is static so calling directly with class name
		if (stepDefinition.place_id == null) {

			m.add_place_payload_with("Reshmi", "SFO-USA", "Tamil", "(+91) 923 588 2389 ",
					"https://rahulshettyacademy.com", 60, -29.467839, 15.865045);
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_Id_created_maps_to_using("Reshmi", "getPlaceAPI");
		}

	}
}
