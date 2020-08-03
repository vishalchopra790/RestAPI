package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        AddPlace a=new AddPlace();
        if(AddPlace.palceId==null) {
            a.add_place_payload_with("Rajesh", "B-28", "Punjabi");
            a.user_calls_with_http_request("AddPlaceAPI", "Post");
            a.verify_place_id_created_maps_to_using("Rajesh", "GetPlaceAPI");

        }

    }
}
