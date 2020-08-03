package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlace(String name,String address ,String language ){
        pojo.AddPlace p = new pojo.AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setName(name);
        p.setLanguage(language);
        p.setPhone_number("9870250566");
        List<String> list = new ArrayList<>();
        list.add("sahil");
        list.add("vishal");
        p.setTypes(list);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
       return p;
    }
    public String deletePlace(String placeId){
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"   \t \t\n" +
                "}\n";
    }
}
