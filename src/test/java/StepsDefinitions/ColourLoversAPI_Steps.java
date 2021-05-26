package StepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
//import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static io.restassured.RestAssured.*;

//import io.restassured.specification.RequestSpecification;

public class ColourLoversAPI_Steps {
	Response response =null;
	@Given("we made Get request to colourlovers api {string}")
	public void we_made_get_request_to_colourlovers_api(String url) throws Throwable{
		//RestAssured.baseURI = "http://www.colourlovers.com/api";
		RequestSpecification request = RestAssured.given().header("User-Agent", "PRETEND_TO_BE_BROWSER");

		request.header("Content-Type", "application/json");
		response = request.get(url).then().log().all().extract().response();
		
		System.out.print(url);
	}
	@Then("Response code should be {int}")
	public void Response_code_should_be(int value) throws Throwable{
	   response.then().statusCode(value);
		
	}
	@And("numViews from response to be greater than {int}")
	public void num_views_from_response_to_be_greater_than(int value) throws Throwable{
		List<Integer> numviews= response.body().xmlPath().getList("patterns.pattern.numViews",Integer.class);
		for(int numview : numviews)
		{
			assertThat(numview, greaterThan(value));
		}
		// response.then().body("patterns.pattern.numViews", greaterThan(value));

	}

}
