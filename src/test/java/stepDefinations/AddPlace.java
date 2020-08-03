package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlace extends Utils {
    RequestSpecification ress;
    ResponseSpecification resspec;
    Response response;
    String resp;
     static String palceId;
    TestDataBuild tdb = new TestDataBuild();

    @Given("Add Place Payload with {string} , {string}, {string}")
    public void add_place_payload_with(String name, String address, String language) throws IOException {
        ress = given().spec(requestSpecification()).body(tdb.addPlace(name, address, language)).log().all();
    }


    @When("user calls {string} with {string} Http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResource apiResource = APIResource.valueOf(resource);

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("Post"))
            response = ress.when().post(apiResource.getResource());
        else if (method.equalsIgnoreCase("Get"))
            response = ress.when().get(apiResource.getResource());

        System.out.println(ress);
    }

    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response is {string}")
    public void in_response_is(String keyValue, String expected) {

        assertEquals(getJsonpath(response, keyValue), expected);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expected) {

        assertEquals(getJsonpath(response, keyValue), expected);
    }

    @Then("verify place_id created maps  to {string} using {string}")
    public  void verify_place_id_created_maps_to_using(String string, String resource) throws IOException {
        palceId = getJsonpath(response, "place_id");
        ress = given().spec(requestSpecification()).queryParam("place_id", palceId);
        user_calls_with_http_request(resource, "Get");
        String name = getJsonpath(response, "name");
        System.out.println(name);
        assertEquals(string,name);

    }


    @Given("Delete Place Pay load")
    public void delete_Place_PayLoad() throws IOException {
        ress = given().spec(requestSpecification()).body(tdb.deletePlace(palceId));
    }
}
