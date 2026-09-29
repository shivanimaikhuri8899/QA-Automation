package QA.pojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
public class SpecBuilder {
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

        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
        ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification reqq=given().spec(req).body(add);
        Response response= reqq.when().post("/maps/api/place/add/json")
                .then().spec(res).extract().response();
        String responseString=response.asString();
        System.out.println(responseString);





    }

}
