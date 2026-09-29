package QA.pojo;

import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Serialize {
    public static void main(String[] args){
        AddPlace add=new AddPlace();
        add.setAccuracy("50");
        add.setAddress("29, side layout, cohen 09");
        add.setName("Frontline house");
        add.setLanguage("French-IN");
        add.setPhone_number("(+91)9838933937");
        add.setWebsite("https://google.com");
        List<String> list=new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        add.setTypes(list);
        Location loc=new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427632);
        add.setLocation(loc);

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123")
                .body(add)
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);

    }

}
